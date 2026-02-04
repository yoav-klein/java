package spring.mvc;

import jakarta.validation.Valid;
import java.util.List;

public class PersonListDTO {
    @Valid // This is the "magic" that tells Spring to go inside the list
    private List<Person> people;


    // Getters and Setters

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}