package spring.mvc;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class Person {
    @NotNull
	@Size(max=64, message="First name no bigger than 64")
	@Size(min=2, message="First name no less than 2")
    private String firstName;

    @NotNull
	@Size(max=64, message="Last name no bigger than 64")
	@Size(min=2, message="Last name no less than 2")
    private String lastName;

    @Min(value=0, message="Age minimum is 0")
    private int age;

    Person() {}

    Person(int age, String firstName, String lastName) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + " " + "(" + this.age + ")";
    }

    
}
