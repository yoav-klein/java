import java.io.*;

class MyString implements Serializable {
    private String a;

    MyString(String data) {
        this.a = data;
    }

    @Override
    public String toString() {
        return this.a;
    }
}
