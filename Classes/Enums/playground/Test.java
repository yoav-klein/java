

public class Test {
    
    public static void main(String[] args) {
        Kid kid = Kid.DAVID;
        System.out.println(kid);

        switch(kid) {
            case DAVID: // an enum switch case label must be the unqualified name of the constant
                System.out.println("Hey David!");
                break;
        }

        if(kid == Kid.DAVID) {
            System.out.println("Hey David");
        }

        for(Kid k : Kid.values()) {
            System.out.println(k);
            System.out.println(k.getAge());
            System.out.println(k.getHobby());
        }
    }
}