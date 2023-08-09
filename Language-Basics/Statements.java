/**
 * There are 3 types of statements:
 * - expressions statements
 * - control flow statements
 * - declaration statements
 */

class Kuku {}

class Expressions {
    static public void main(String[] args) {
        
        // expression statements are expressions ended with ;
        // all the followings are expression statments
        int a = 0;
        Kuku k = new Kuku();
        a++;
        a = 3;
        // a method call ended with ; is also an expression statement
        System.out.println("hello");


        // delcaration statements
        Kuku k1; 
        int t;

        // control flow statements
        if(a == 3) {
            System.out.println("a is 3");
        }


    }
}