
package com.example.test;

import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ActiveProfiles;
import com.example.business.SpringBusinessConfig;

@ContextConfiguration(classes = { SpringBusinessConfig.class })
@ActiveProfiles("test")
public abstract class BusinessTestBase extends AbstractTransactionalTestNGSpringContextTests {
    
}
