
// this example is taken from the Oracle Java tutorial, with some additions of my own

public class OuterClass {


    String outerField = "Outer field";
    static String staticOuterField = "Static outer field";
    private String outerFieldPrivate = "Outer field private";
    private static String staticOuterFieldPrivate = "Static outer field private";

    // inner class - non-static nested class
    class InnerClass {
        void accessMembers() {
            // can access both static and non-static members of the outer class
            System.out.println(outerField);
            System.out.println(staticOuterField);
            // also can access private members
            System.out.println(outerFieldPrivate);
            System.out.println(staticOuterFieldPrivate);
            
        }
    }

    // static nested class
    static class StaticNestedClass {
        void accessMembers(OuterClass outer) {
            // Compiler error: Cannot make a static reference to the non-static
            //     field outerField
            // System.out.println(outerField);
            
            // can access static member directly, also to private
            System.out.println(staticOuterField);
            System.out.println(staticOuterFieldPrivate);
            // instance members can be accessed only through an instance, since it's a static method
            System.out.println(outer.outerField);
            System.out.println(outer.outerFieldPrivate);
        }
    }

    public static void main(String[] args) {
        System.out.println("Inner class:");
        System.out.println("------------");
        OuterClass outerObject = new OuterClass();
        OuterClass.InnerClass innerObject = outerObject.new InnerClass();
        innerObject.accessMembers();

        System.out.println("\nStatic nested class:");
        System.out.println("--------------------");
        StaticNestedClass staticNestedObject = new StaticNestedClass();        
        staticNestedObject.accessMembers(outerObject);
        
        System.out.println("\nTop-level class:");
        System.out.println("--------------------");
        TopLevelClass topLevelObject = new TopLevelClass();        
        topLevelObject.accessMembers(outerObject);                
    }
}