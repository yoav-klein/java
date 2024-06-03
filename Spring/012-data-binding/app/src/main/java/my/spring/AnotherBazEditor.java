package my.spring;

import java.beans.PropertyEditorSupport;

public class AnotherBazEditor extends PropertyEditorSupport {
    public void setAsText(String text) {
        setValue(new Baz(text.toUpperCase()));
    }
}
