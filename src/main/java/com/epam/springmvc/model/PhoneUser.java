package com.epam.springmvc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneUser {

    private int id;
    private String fullName;

    public PhoneUser() {
    }

    public PhoneUser(String fullName) {
        this();
        this.fullName = fullName;
    }

}
