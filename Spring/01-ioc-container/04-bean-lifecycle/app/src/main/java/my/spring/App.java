/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package my.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    
    public static void main(String[] args) {
        
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Dog obj = (Dog) context.getBean("dog");
        obj.getMessage();

        Cat cat = (Cat) context.getBean("cat");
        Elephant elephant = (Elephant) context.getBean("elephant");
        Elegator elegator = (Elegator) context.getBean("elegator");

        context.close();
        
    }
}