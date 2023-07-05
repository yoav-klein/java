/**
 * Specifying search criteria in lambda expression
 * 
 * 
 */
import java.util.List;


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

         printPersons(
            roster,
            (Person p) -> p.getGender() == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 25
        );
    }


}