package spring.mvc;

import java.util.List;
import java.util.ArrayList;

public class PersonDto {
    private List<Person> persons = new ArrayList<>();

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
    
    void addPerson(Person p) {
        persons.add(p);
    }
}
