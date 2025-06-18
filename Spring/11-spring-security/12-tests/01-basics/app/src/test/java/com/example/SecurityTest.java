

package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import  static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.business.SpringBusinessConfig;
import com.example.business.service.MessageService;
import com.example.web.SpringSecurityConfig;
import com.example.web.SpringWebConfig;

@WebAppConfiguration
@ContextConfiguration(classes = { SpringWebConfig.class, SpringSecurityConfig.class, SpringBusinessConfig.class })
public class SecurityTest extends AbstractTestNGSpringContextTests {
    
	@Autowired
	private WebApplicationContext context;

	@Autowired
	private MessageService messageService;

	private MockMvc mvc;

	@BeforeMethod
	public void setup() {
		mvc = webAppContextSetup(context)
            .apply(SecurityMockMvcConfigurers.springSecurity())
            .build();
	}

	// the /admin endpoint is only allowed for users with the ADMIN role.
    @Test
    public void testAdmin() throws Exception {
        mvc.perform(get("/admin").with(user("eliyahu").roles("ADMIN"))).andExpect(status().isOk());
    }

	// demonstrates the use of method security testing
	// the messageService.getMessage() method is only allowed for 'yoav'
    @Test
	@WithMockUser("yoav")
    public void testMethodSecurity() throws Exception {
        messageService.getMessage();
    }

	/* this doesn't work I don't know why 
	
	@Test // (expectedExceptions=AuthenticationCredentialsNotFoundException.class)
	@WithMockUser("yoav")
	public void getMessageUnauthenticated() {
		messageService.onlyAuthenticated();
		try {
			messageService.onlyAuthenticated();
		} catch(Exception e) {
			System.out.println(e.getClass().toString());
		}
	} */

	// demonstrates the use of method security with MockMVC
	// the /methodsecurity endpoint calls the messageService.getMessage() method
    @Test
    public void testMethodSecurityWithMockMVC() throws Exception {
        mvc.perform(get("/methodsecurity").with(user("yoav"))).andExpect(status().isOk());
    }

	@Test
	@WithMockUser("yoav")
    public void testMethodSecurityWithMockMVC2() throws Exception {
        mvc.perform(get("/methodsecurity")).andExpect(status().isOk());
    }

	// assert that a certain user exists
	@Test
	public void testFormLogin() throws Exception {
		 mvc.perform(formLogin().user("admin")).andExpect(authenticated());
	}


	

	
}
