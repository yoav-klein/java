package my.spring;

import java.beans.PropertyEditorSupport;

public class SomeBazEditor extends PropertyEditorSupport {
    public void setAsText(String text) {
        setValue(new Baz(text.toLowerCase()));
    }
}
