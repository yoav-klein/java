package my.spring;

import java.beans.PropertyEditorSupport;

public class TheAddressEditor extends PropertyEditorSupport {
    public void setAsText(String text) {
        String[] parts = text.split(",");
        String city = parts[0];
        String street = parts[1];
        int num = Integer.parseInt(parts[2]);

        setValue(new Address(city, street, num));
    }
}