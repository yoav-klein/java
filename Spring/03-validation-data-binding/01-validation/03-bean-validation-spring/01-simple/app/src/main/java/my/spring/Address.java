package my.spring;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Address {
    
    public Address() {}
    
    public Address(String cityName,  String streetName, int streetNumber) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.cityName = cityName;
    }

    
    @Min(0)
    private int streetNumber;

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    @NotNull
	@Size(max=64)
    private String streetName;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @NotNull
	@Size(max=64)
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
