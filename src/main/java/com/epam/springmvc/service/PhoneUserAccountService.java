package com.epam.springmvc.service;

import com.epam.springmvc.model.PhoneUserAccount;

import java.util.List;
import java.util.Set;

public interface PhoneUserAccountService {

    List<PhoneUserAccount> findAll();

    PhoneUserAccount getById(int id);

    void deleteById(int id);

    void update(PhoneUserAccount phoneUserAccount);

    int save(PhoneUserAccount phoneUserAccount);

    int getUserIdByPhoneNumber(String phoneNumber);

    Set<String> getPhoneNumberValuesByUserId(int userId);

    List<PhoneUserAccount> getPhoneNumbersByPhoneUserId(int userId);

    PhoneUserAccount getPhoneUserAccountByPhoneNumber(String phoneNumber);

    boolean checkIfExistsByValue(String phoneNumberValue);

    int getNumberOfPhonesNumbersByUserId(int id);

    void changeMobileOperator(String phoneNumber, String newOperator);
}
