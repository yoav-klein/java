/**
 * a more generalized approach
 * 
 * in this approach, the search method allows the user to specify a range of ages.
 * 
 * But still, what if you want to search based on several criterias, like age and sex, etc.?
 * 
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

     public static void printPersonsWithinAgeRange(
        List<Person> roster, int low, int high) {
        for (Person p : roster) {
            if (low <= p.getAge() && p.getAge() < high) {
                p.printPerson();
            }
        }
    }


    public static void main(String[] args) {
        printPersonsWithinAgeRange(roster, 18, 25);
    }
}