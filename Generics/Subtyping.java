
class A<T> {
    private T t;

    void set(T t) {
        this.t = t;
    }

    T get() {
        return t;
    }
}

class B<T> extends A<T> {
    private T t;
    
    void set(T t) {
        this.t = t;
    }

    T get() {
        return t;
    }
}

class Test {
    public static void printAString(A<String> s) {
        System.out.println(s.get());
    } 

    public static void main(String[] args) {
        B<String> s = new B<>();
        s.set("Yooo...");

        // since B<String> is a A<String>, we can pass B<String> to printAString
        printAString(s);
    }
}