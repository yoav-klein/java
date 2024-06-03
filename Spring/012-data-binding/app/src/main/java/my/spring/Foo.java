package my.spring;

public class Foo {
    private Bar bar;
    private Baz baz;
    Class<?> clazz;

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void printClazz() {
        System.out.println(clazz);
    }

    public void setBaz(Baz baz) { 
        this.baz = baz;
    }

    public void printBaz() {
        baz.print();
    }

    public void setBar(Bar bar) { 
        this.bar = bar;
    }

    public void printBar() {
        bar.print();
    }
}
