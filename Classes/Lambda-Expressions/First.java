/**
 * This is the most naive implementation
 * this code is brittle, since the search method relies on the Person class
 * has specific methods.
 * 
 * Additionally, you'll need to implement a method for each search criteria.
 * 
 * Additionally, the search method is restrictive - what if we want to find persons 
 * within a specific range of ages, not only older than?
 */

class Person {
    String name;
    public enum Sex {
        MALE, FEMALE
    }

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;

    public int getAge() {
        // imagine us implementing this
        return 19;
    }

    public void printPerson() {
        System.out.println(name + " and some other stuff");
    }


    // imagine having some more search methods, like based on sex.
}

class RosterTest {
    List<Person> = new ArrayList<Person>;

     public static void printPersonsOlderThan(
        List<Person> roster, int age) {
        for (Person p : roster) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }
    }

    public static void main(String[] args) {
        printPersonsWithinAgeRange(roster, 18, 25);
    }
}