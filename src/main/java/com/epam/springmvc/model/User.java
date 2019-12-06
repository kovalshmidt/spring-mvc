package com.epam.springmvc.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private int id;
    private String fullName;
    private String email;
    private String password;

    public User() {
    }

    public User(String fullName) {
        this.fullName = fullName;
    }

}
