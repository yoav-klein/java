package spring.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import spring.mvc.business.BusinessConfig;
import spring.mvc.business.exception.BadUserException;
import spring.mvc.web.WebConfig;

@WebAppConfiguration
@ContextConfiguration(classes = { WebConfig.class, BusinessConfig.class })
public class MyTest extends AbstractTestNGSpringContextTests {
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;

	@BeforeMethod
	void setup() {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

    // basic test, assert that status is ok and model contains a certain attribute
    @Test
    void testHello() throws Exception {
        mockMvc.perform(get("/hello")).andExpect(status().isOk())
        .andExpect(model().attributeExists("name"));
    }

    // assert that a specific exception was thrown
    // NOTE: this works only if the exception was handled by an exception resolver
    @Test
    void testException() throws Exception {
        mockMvc.perform(get("/user").param("name", "yoav"))
            .andExpect(result -> {
                Exception ex = result.getResolvedException();
                Assert.isInstanceOf(BadUserException.class, ex);
            });
    }

    // expect a certain status code
    @Test
    void testStatusCode() throws Exception {
        mockMvc.perform(get("/server-error")).andExpect(status().is5xxServerError());
    }
}