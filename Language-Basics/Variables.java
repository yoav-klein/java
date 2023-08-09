/**
 * The following types of variables exist:
 * - Instance variables (Non-static fields)
 * - Class variables (Static fields)
 * - Local variables
 * - Parameters
 */

class SomeClass {
    static int classVariable = 20;
}

class Variables {
    int instanceVariable = 10;
    /* method parameter */
    static public void printParams(int param) {
        System.out.printf("Parameter: %d", param);
    }

    static public void main(String[] args) {
        int localVariable = 1;

        Variables vars = new Variables();
        System.out.printf("Instance variables: %d\n", vars.instanceVariable);
        System.out.printf("Class variables: %d\n", SomeClass.classVariable);
        System.out.printf("Local variable: %d\n", localVariable);
        printParams(21);
    }
}