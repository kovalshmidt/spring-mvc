package com.epam.springmvc.dao;

import com.epam.springmvc.model.PhoneUserAccount;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PhoneUserAccountDao {

    List<PhoneUserAccount> findAll();

    PhoneUserAccount getById(int id);

    void deleteById(int id);

    void update(PhoneUserAccount phoneUserAccount);

    int save(PhoneUserAccount phoneUserAccount);

    int getUserIdByPhoneNumber(String phoneNumberValue);

    Set<String> getPhoneNumberValuesByUserId(int userId);

    List<PhoneUserAccount> getPhoneUserAccountByUserId(int userId);

    PhoneUserAccount getPhoneUserAccountByPhoneNumber(String phoneNumber);

    int checkIfExistsByPhoneNumber(String phoneNumber);

    int getNumberOfPhonesNumbersByUserId(int id);
}
