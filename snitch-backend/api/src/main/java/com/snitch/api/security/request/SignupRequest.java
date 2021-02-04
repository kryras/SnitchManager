package com.snitch.api.security.request;

public class SignupRequest {

    private String firstName;
    private String lastName;
    private String position;
    private String username;
    private String password;
    private String email;

    public SignupRequest() {
    }

    public SignupRequest(String firstName, String lastName, String position, String username, String password,
            String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.username = username;
        this.password = password;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
