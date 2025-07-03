package my.spring;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = {AppConfig.class})
public class BasicTest extends AbstractTransactionalTestNGSpringContextTests  {
    
    // the user added to the database will be rolled-back after the test method is done
    // due to Transaction Managament support
    @Test
    public void testDatabase() {
        jdbcTemplate.update("INSERT INTO users VALUES(1, 'yoav')");
        String name = jdbcTemplate.queryForObject("SELECT name FROM users WHERE id = 1", String.class);
        System.out.println(name);
    }
}

