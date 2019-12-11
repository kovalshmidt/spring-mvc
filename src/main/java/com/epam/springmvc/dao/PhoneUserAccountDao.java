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

    List<PhoneUserAccount> getPhoneNumberByPhoneUserId(int userPhoneId);

    PhoneUserAccount getPhoneNumberByValue(String phoneNumberValue);

    int checkIfExistsByValue(String phoneNumberValue);

    int getNumberOfPhonesNumbersByUserId(int id);
}
