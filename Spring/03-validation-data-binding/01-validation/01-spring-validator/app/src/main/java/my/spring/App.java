/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package my.spring;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.validation.Validator;
import org.springframework.validation.SimpleErrors;

public class App {
    
    public static void main(String[] args) {
        
        // ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Person yoav = new Person();
        yoav.setAge(-3);
        yoav.setName("Yoav");

        PersonValidator validator = new PersonValidator();

        SimpleErrors errors = new SimpleErrors(yoav);
        validator.validate(yoav, errors);

        if (errors.hasErrors()) {
            // Print out the errors
            errors.getAllErrors().forEach(error -> System.out.println(error.toString()));
        } else {
            System.out.println("Form is valid.");
        }

        
    }
}