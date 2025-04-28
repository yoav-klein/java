package spring.mvc;

import java.util.List;

import spring.mvc.Person;

import java.util.ArrayList;

public class DivisionDto {
     private List<Division> divisions = new ArrayList<>();

    public List<Division> getDivisions() {
        return divisions;
    }

    public void setDivisions(List<Division> divisions) {
        this.divisions = divisions;
    }
    
    void addDivision(Division d) {
        divisions.add(d);
    }
}
