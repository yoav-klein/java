/**
 * Specifying search criteria in local classes
 * 
 * in this approach, we have a generic printPersons method, which takes a CheckPerson
 * object as a parameter. 
 * 
 * CheckPerson is an interface.
 * 
 * then you can create several classes that implement that interface.
 * 
 * This is better than the second approach, but there's still a lot of code - additional class
 * for each implementation.
 * 
 */


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
        List<Person> roster = Person.createRoster();

        class CheckPersonEligibleForSelectiveService implements CheckPerson {
            public boolean test(Person p) {
                return p.gender == Person.Sex.MALE &&
                    p.getAge() >= 18 &&
                    p.getAge() <= 25;
            }
        }

        printPersons(roster, new CheckPersonEligibleForSelectiveService());
    }


}