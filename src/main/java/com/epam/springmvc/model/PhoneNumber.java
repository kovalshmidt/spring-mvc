package com.epam.springmvc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneNumber {

    private long id;
    private String phoneNumber;
    private int userId;
    private int phoneCompanyId;

    public PhoneNumber() {
    }

    public PhoneNumber(String phoneNumber, int userId, int phoneCompanyId) {
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.phoneCompanyId = phoneCompanyId;
    }
}
