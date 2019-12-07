package com.epam.springmvc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneNumber {

    private long id;
    private String phoneNumberValue;
    private int phoneUserId;
    private int phoneCompanyId;

    public PhoneNumber() {
    }

    public PhoneNumber(String phoneNumberValue, int phoneUserId, int phoneCompanyId) {
        this.phoneNumberValue = phoneNumberValue;
        this.phoneUserId = phoneUserId;
        this.phoneCompanyId = phoneCompanyId;
    }
}
