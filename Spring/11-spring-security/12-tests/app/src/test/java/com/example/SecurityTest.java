

package com.example;

import com.example.web.SpringSecurityConfig;
import com.example.web.SpringWebConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import org.springframework.test.context.*;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MvcResult;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.*;
import static org.testng.Assert.*;

@WebAppConfiguration
@ContextConfiguration(classes = { SpringWebConfig.class, SpringSecurityConfig.class })
public class SecurityTest extends AbstractTestNGSpringContextTests {
    
	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@BeforeMethod
	public void setup() {
		mvc = webAppContextSetup(context)
            .apply(SecurityMockMvcConfigurers.springSecurity())
            .build();
	}

    @Test
    public void fooTest() throws Exception {
        mvc.perform(get("/admin").with(user("eliyahu").roles("ADMIN"))).andExpect(status().isOk());
    }
}
