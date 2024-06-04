
package spring.mvc;

import java.beans.PropertyEditorSupport;

import spring.mvc.Person;

public class CustomPersonEditor extends PropertyEditorSupport {
    public void setAsText(String text) {
        String[] parts = text.split(",");

        Person p = new Person();
        Address addr = new Address();
        
        p.setFirstName(parts[0]);
        p.setLastName(parts[1]);

        addr.setStreet(parts[2]);
        addr.setCity(parts[3]);

        p.setAddress(addr);
        
        setValue(p);
    }
}