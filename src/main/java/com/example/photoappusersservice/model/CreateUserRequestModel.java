package com.example.photoappusersservice.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateUserRequestModel {

    @NotNull(message = "First name should not be empty")
    @Size(min = 2, message = "First name cannot be less than 2 characters")
    private String firstName;
    @NotNull(message = "Last name should not be empty")
    @Size(min = 2, message = "Last name cannot be less than 2 characters")
    private String lastName;
    @NotNull(message = "Password cannot be empty")
    @Size(min = 8, max = 16, message = "Password should have between 8 to 16 characters")
    private String password;
    @NotNull(message = "Email cannot be empty")
    @Email
    private String email;

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
