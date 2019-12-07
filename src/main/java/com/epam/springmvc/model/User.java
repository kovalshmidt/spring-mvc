package com.epam.springmvc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private int id;
    private String email;
    private String password;
    private String roles;
    private int phoneUserId;

    public User() {
        this.roles = "REGISTERED_USER";
    }
}
