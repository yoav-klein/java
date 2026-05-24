package com.example.business.model;

public class AppUser {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String displayName;
    private String pictureUrl;

    public AppUser() {
    }

    public AppUser(String id, String email, String firstName, String lastName, String displayName, String pictureUrl) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.pictureUrl = pictureUrl;
    }

    public AppUser(String id, String email, String firstName, String lastName, String pictureUrl) {
        this(id, email, firstName, lastName, firstName + " " + lastName, pictureUrl);
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }


}
