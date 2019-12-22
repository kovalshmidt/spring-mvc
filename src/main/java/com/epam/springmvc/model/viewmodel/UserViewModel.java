package com.epam.springmvc.model.viewmodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserViewModel {

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String phoneCompany;
    private int amount;

    public UserViewModel() {
    }
}
