/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package my.spring;
import org.springframework.validation.DataBinder;
import org.springframework.beans.MutablePropertyValues;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        // Create a new instance of the Person class
        Person person = new Person();

        // Create a DataBinder for the Person object
        DataBinder dataBinder = new DataBinder(person);

        // Create a map with property values to bind
        Map<String, Object> propertyValues = new HashMap<>();
        propertyValues.put("name", "John Doe");
        propertyValues.put("age", 30);

        // Bind the map values to the person object
        dataBinder.bind(new MutablePropertyValues(propertyValues));

        // Output the bound object
        System.out.println(person);
    }
}