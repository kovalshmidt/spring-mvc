package com.epam.springmvc.service;

import com.epam.springmvc.model.PhoneUserAccount;

import java.util.List;
import java.util.Set;

public interface PhoneNumberService {

    List<PhoneUserAccount> findAll();

    PhoneUserAccount getById(int id);

    void deleteById(int id);

    void update(PhoneUserAccount phoneUserAccount);

    int save(PhoneUserAccount phoneUserAccount);

    int getUserIdByPhoneNumber(String phoneNumberValue);

    Set<String> getPhoneNumberValuesByUserId(int userId);

    List<PhoneUserAccount> getPhoneNumbersByPhoneUserId(int userId);

    PhoneUserAccount getPhoneNumberByValue(String phoneNumber);

    boolean checkIfExistsByValue(String phoneNumberValue);
}
