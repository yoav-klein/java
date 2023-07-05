/**
 * Specifying search criteria in anonymous classes
 * 
 * in this approach, instead of declaring a class, we use anonymous class to create an object
 * and passing it to the printPersons method
 * 
 * This is better than the third approach, but the syntax of an anonymous class for a
 * class that has only one method is cumbersome.
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

}


class RosterTest {
    interface CheckPerson {
        boolean test(Person p);
    }

    static public void printPersons(List<Person> roster, CheckPerson tester) {
        for(Person p : roster) {
            if(tester.test(p)) {
                p.printPerson();
            }
        }
    }

    static public void main(String[] args) {
         printPersons(
            roster,
            new CheckPerson() {
                public boolean test(Person p) {
                    return p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25;
                }
            }
        );
    }


}