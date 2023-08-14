
/**
 * 
 * methods that are overriden in other candidates are ignored
 * 
 * In this case, the candidates are EggLayer and FireBreather.
 * However, EggLayer overriden the method, so it takes precedence
 * 
 */

interface Animal {
    default public String identifyMyself() {
        return "I am an animal.";
    }
}

interface EggLayer extends Animal {
    default public String identifyMyself() {
        return "I am able to lay eggs.";
    }
}

interface FireBreather extends Animal { }

class Dragon implements EggLayer, FireBreather {
    public static void main (String... args) {
        Dragon myApp = new Dragon();
        System.out.println(myApp.identifyMyself());
    }
}