package com.epam.springmvc.service;

import com.epam.springmvc.model.PhoneNumber;

import java.util.List;
import java.util.Set;

public interface PhoneNumberService {

    List<PhoneNumber> findAll();

    PhoneNumber getById(int id);

    void deleteById(int id);

    void update(PhoneNumber phoneNumber);

    int save(PhoneNumber phoneNumber);

    int getUserIdByPhoneNumber(String phoneNumberValue);

    Set<String> getPhoneNumberValuesByUserId(int userId);

    List<PhoneNumber> getPhoneNumbersByUserId(int userId);

    PhoneNumber getPhoneNumberByValue(String phoneNumber);

    boolean checkIfExistsByValue(String phoneNumberValue);
}
