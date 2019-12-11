package com.epam.springmvc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneUserAccount {

    private long id;
    private String phoneNumber;
    private int phoneUserId;
    private int phoneCompanyId;

    public PhoneUserAccount() {
    }

    public PhoneUserAccount(String phoneNumber, int phoneUserId, int phoneCompanyId) {
        this.phoneNumber = phoneNumber;
        this.phoneUserId = phoneUserId;
        this.phoneCompanyId = phoneCompanyId;
    }
}
