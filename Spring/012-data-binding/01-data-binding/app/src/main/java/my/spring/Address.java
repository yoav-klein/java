
package my.spring;

public class Address {
    private String city;
    private String street;
    int number;

    

    public Address(String city, String street, int number) {
        this.city = city;
        this.street = street;
        this.number = number;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    
}