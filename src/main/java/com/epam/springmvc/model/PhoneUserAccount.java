package com.epam.springmvc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneUserAccount {

    private long id;
    private String phoneNumber;
    private int userId;
    private int phoneCompanyId;
    private int amount;

    public PhoneUserAccount() {
    }

    public PhoneUserAccount(String phoneNumber, int userId, int phoneCompanyId, int amount) {
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.phoneCompanyId = phoneCompanyId;
        this.amount = amount;
    }
}
