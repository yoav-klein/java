
class External {
    public static void main(String[] args) {
        Outer o = new Outer();
       
        Outer.Inner i1 = o.new Inner();
        Outer.Inner i2 = o.new Inner();

        o.print();
        i1.changeOutterValue(10);
        o.print();
        i2.changeOutterValue(20);
        o.print();

        // Outer.PrivateInner pi = o.new PrivateInner();  -> compiler error - PrivateInner is declared private
       
    }
}