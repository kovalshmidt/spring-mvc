package com.epam.springmvc.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class PhoneUserInfo {

    private int phoneUserId;
    private String fullName;
    private String email;
    private String roles;
    private Map<String, String> phoneInfo;

    public PhoneUserInfo() {
    }
}
