
public class Box<T> {
    T elem;
    void set(T elem) {
        this.elem = elem;
    }

    T get() {
        return this.elem;
    }
}