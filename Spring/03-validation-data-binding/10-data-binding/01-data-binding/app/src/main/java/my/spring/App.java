/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package my.spring;
import org.springframework.validation.DataBinder;
import org.springframework.validation.BindingResult;
import org.springframework.beans.MutablePropertyValues;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        // Create a new instance of the Person class
        Person person = new Person();

        // Create a DataBinder for the Person object
        DataBinder dataBinder = new DataBinder(person);

        dataBinder.registerCustomEditor(Address.class, new TheAddressEditor());
        
        // Create a map with property values to bind
        Map<String, Object> propertyValues = new HashMap<>();
        propertyValues.put("name", "John Doe");
        propertyValues.put("age", "30");
        propertyValues.put("address", "Karmiel,Marva,4");
        // propertyValues.put("age", "kapara"); // will produce a binding error

        // Bind the map values to the person object
        dataBinder.bind(new MutablePropertyValues(propertyValues));

        BindingResult result = dataBinder.getBindingResult();
        if(result.hasErrors()) { 
            System.out.println("Biding failed");
            System.exit(1);
        }

        System.out.println(result.getErrorCount());

        // Output the bound object
        System.out.println(person);
    }
}