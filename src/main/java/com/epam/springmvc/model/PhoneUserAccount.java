package com.epam.springmvc.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PhoneUserAccount {

    private long id;
    private String phoneNumber;
    private int userId;
    private int phoneCompanyId;
    private BigDecimal amount;

    public PhoneUserAccount() {
    }

    public PhoneUserAccount(String phoneNumber, int userId, int phoneCompanyId) {
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.phoneCompanyId = phoneCompanyId;
    }
}
