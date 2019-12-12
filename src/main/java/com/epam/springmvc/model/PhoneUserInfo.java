package com.epam.springmvc.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PhoneUserInfo {

    private int phoneUserId;
    private String name;
    private String surname;
    private String email;
    private String roles;
    private Set<PhoneInfo> phoneInfoSet;

    public PhoneUserInfo() {}

    @Getter
    @Setter
    public static class PhoneInfo {
        private String phoneNumber;
        private String phoneCompany;
        private int balance;

        public PhoneInfo(){}

        public PhoneInfo(String phoneNumber, String phoneCompany, int balance) {
            this.phoneNumber = phoneNumber;
            this.phoneCompany = phoneCompany;
            this.balance = balance;
        }
    }
}

