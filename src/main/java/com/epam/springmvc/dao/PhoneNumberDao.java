package com.epam.springmvc.dao;

import com.epam.springmvc.model.PhoneNumber;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PhoneNumberDao {

    List<PhoneNumber> findAll();

    PhoneNumber getById(int id);

    void deleteById(int id);

    void update(PhoneNumber phoneNumber);

    int save(PhoneNumber phoneNumber);

    int getUserIdByPhoneNumber(String phoneNumberValue);

    Set<String> getPhoneNumberValuesByUserId(int userId);

    List<PhoneNumber> getPhoneNumberByUserId(int userId);
}
