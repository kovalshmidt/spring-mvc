package com.epam.springmvc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String roles;

    public User() {
        this.roles = "REGISTERED_USER";
    }

    public User(String name, String surname) {
        this();
        this.name = name;
        this.surname = surname;
    }
}
