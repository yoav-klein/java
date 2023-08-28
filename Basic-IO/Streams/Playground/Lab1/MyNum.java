import java.io.*;

class MyNum implements Serializable {
    private int a;

    MyNum(int data) {
        this.a = data;
    }

    @Override
    public String toString() {
        return String.format("%d", a);
    }
}
