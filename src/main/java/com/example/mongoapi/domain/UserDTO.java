package com.example.mongoapi.domain;

public class UserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String gender;

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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public User getUser() {
        User user = new User();
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setGender(this.gender);
        return user;
    }

    public User updateUser(User user) {
        if(isNotNull(firstName)) user.setFirstName(firstName);
        if(isNotNull(lastName)) user.setLastName(lastName);
        if(isNotNull(email)) user.setEmail(email);
        if(isNotNull(gender)) user.setGender(gender);
        return user;
    }

    private Boolean isNotNull(Object value) {
        return value != null;
    }
    
}
