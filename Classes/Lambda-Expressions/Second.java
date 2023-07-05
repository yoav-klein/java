/**
 * a more generalized approach
 * 
 * in this approach, the search method allows the user to specify a range of ages.
 * 
 * But still, what if you want to search based on several criterias, like age and sex, etc.?
 * 
 */


class RosterTest {
    
     public static void printPersonsWithinAgeRange(
        List<Person> roster, int low, int high) {
        for (Person p : roster) {
            if (low <= p.getAge() && p.getAge() < high) {
                p.printPerson();
            }
        }
    }


    public static void main(String[] args) {
        List<Person> roster = Person.createRoster();

        printPersonsWithinAgeRange(roster, 18, 25);
    }
}