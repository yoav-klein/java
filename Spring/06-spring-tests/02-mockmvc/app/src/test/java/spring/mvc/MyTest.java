package spring.mvc;

import org.springframework.test.context.web.*;
import org.springframework.test.context.*;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import spring.mvc.web.WebConfig;

@WebAppConfiguration
@ContextConfiguration(classes = { WebConfig.class })
public class MyTest extends AbstractTestNGSpringContextTests {
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;

	@BeforeMethod
	void setup() {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

    @Test
    void foo() throws Exception {
        mockMvc.perform(get("/hello")).andExpect(status().isOk())
        .andExpect(model().attributeExists("name"));
    }

    
}