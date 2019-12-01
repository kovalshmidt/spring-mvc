package com.epam.springmvc.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneCompany {

    private int id;
    private String companyName;

    public PhoneCompany() {
    }

    public PhoneCompany(String companyName) {
        this.companyName = companyName;
    }
}
