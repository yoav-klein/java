
class Operators {
    static public void main(String[] args) {
        // assignment operator
        int a = 1;
        int b = 2;
        // arithmetic operators
        int c = a + b;
        c = a - b;
        c = a * b;
        c = b / a;
        c = a % b;

        // unary operators
        int d = +2;
        d = -2;
        d = d++; // d = -2
        d = ++d; // d = -1
        System.out.println(d);

        boolean bo = true;
        bo = !bo; // inverts to false;
        bo = !bo; // true again

        // equality operators
        int i1 = 20;
        int i2 = 30;
        
        boolean check = i1 < i2;
        check = i1 > i2;
        check = i1 == i2;
        check = i1 >= i2;
        check = i1 <= i2;
        check = i1 != i2;

        int i3 = 2;
        int i4 = 5;

        // conditional operators
        check = (i1 < i2) && (i3 < i4);
        System.out.println(check);
        check = (i1 > i2) || (i3 < i4);
        System.out.println(check);
        
        // instanceof operator
        Operators op = new Operators();
        check = op instanceof Operators;

    }
}