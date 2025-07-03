package my.spring;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

// sometimes you need to do some setup before all the methods in the class
// lifecycle methods are not inside a transaction
// so you need to do the destroy logic in the AfterClass method

@ContextConfiguration(classes = {AppConfig.class})
public class AnotherTest extends AbstractTransactionalTestNGSpringContextTests  {
    @BeforeClass
    public void addUser() {
        jdbcTemplate.update("INSERT INTO users VALUES(2, 'eli')");
    }

    @Test
    public void testDatabase() {
        String name = jdbcTemplate.queryForObject("SELECT name FROM users WHERE id = 2", String.class);
        System.out.println(name);
    }

    @AfterClass
    public void deleteUser() {
        jdbcTemplate.update("DELETE FROM users WHERE id = 2");
    }
}

