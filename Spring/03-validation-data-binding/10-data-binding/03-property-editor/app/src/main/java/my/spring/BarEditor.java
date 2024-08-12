package my.spring;

import java.beans.PropertyEditorSupport;

public class BarEditor extends PropertyEditorSupport {
    public void setAsText(String text) {
        setValue(new Bar(text.toUpperCase()));
    }
}
