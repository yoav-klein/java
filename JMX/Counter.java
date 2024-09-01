public class Counter implements CounterMBean {
    private int value = 0;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void increment() {
        value++;
    }
}
