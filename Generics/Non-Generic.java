
class Box {
    Object obj;

    void set(Object obj) {
        this.obj = obj; 
    }
    Object get() {
        return this.obj;
    }

    static public void main(String[] args) {
        Box b = new Box();
        
        Integer i = 20;
        b.set(i);

        b.set("Hello");

        Integer i1 = (Integer)b.get();
    }
}