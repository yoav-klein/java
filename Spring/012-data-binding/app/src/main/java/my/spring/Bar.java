package my.spring;

public class Bar {
    private String value;
    // private int number;

    public Bar(String val) {
        // this.number = number;
        this.value = val;
    }

    public void print() {
        System.out.println(this.value);
       // System.out.println(this.number);
    }
}
