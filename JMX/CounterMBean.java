public interface CounterMBean {
    int getValue();
    void setValue(int value);
    void increment();
}