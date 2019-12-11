package com.epam.springmvc.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PhoneUserAccount {

    private long id;
    private String phoneNumber;
    private int phoneUserId;
    private int phoneCompanyId;
    private BigDecimal amount;

    public PhoneUserAccount() {
    }

    public PhoneUserAccount(String phoneNumber, int phoneUserId, int phoneCompanyId) {
        this.phoneNumber = phoneNumber;
        this.phoneUserId = phoneUserId;
        this.phoneCompanyId = phoneCompanyId;
    }
}
