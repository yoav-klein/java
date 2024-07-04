import java.util.function.Supplier;

public class Test {
    static <T> void takeSupplier(Supplier<T> supplier) {
        supplier.get();
    }

    public static void main(String[] args) {
        takeSupplier(() -> { return new Test(); });
    }


}
