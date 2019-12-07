package com.epam.springmvc.service;

import com.epam.springmvc.model.PhoneUser;

import java.util.List;


public interface PhoneUserService {

    List<PhoneUser> findAll();

    PhoneUser getById(int id);

    void deleteById(int id);

    void update(PhoneUser phoneUser);

    int save(PhoneUser phoneUser);

    int getNumberOfPhonesNumbersById(int id);

    int checkIfUserExistsByFullName(String fullname);
}
