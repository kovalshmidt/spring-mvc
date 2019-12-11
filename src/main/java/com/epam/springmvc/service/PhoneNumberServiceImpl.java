package com.epam.springmvc.service;

import com.epam.springmvc.dao.PhoneNumberDao;
import com.epam.springmvc.model.PhoneUserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    private PhoneNumberDao phoneNumberDao;

    @Autowired
    public PhoneNumberServiceImpl(PhoneNumberDao phoneNumberDao) {
        this.phoneNumberDao = phoneNumberDao;
    }

    @Override
    public List<PhoneUserAccount> findAll() {
        return phoneNumberDao.findAll();
    }

    @Override
    public PhoneUserAccount getById(int id) {
        return phoneNumberDao.getById(id);
    }

    @Override
    public void deleteById(int id) {
        phoneNumberDao.deleteById(id);
    }

    @Override
    public void update(PhoneUserAccount phoneUserAccount) {
        phoneNumberDao.update(phoneUserAccount);
    }

    @Override
    public int save(PhoneUserAccount phoneUserAccount) {
        return phoneNumberDao.save(phoneUserAccount);
    }

    @Override
    public int getUserIdByPhoneNumber(String phoneNumberValue) {
        return phoneNumberDao.getUserIdByPhoneNumber(phoneNumberValue);
    }

    @Override
    public Set<String> getPhoneNumberValuesByUserId(int userId) {
        return phoneNumberDao.getPhoneNumberValuesByUserId(userId);
    }

    @Override
    public List<PhoneUserAccount> getPhoneNumbersByPhoneUserId(int userId) {
        return phoneNumberDao.getPhoneNumberByPhoneUserId(userId);
    }

    @Override
    public PhoneUserAccount getPhoneNumberByValue(String phoneNumber) {
        return phoneNumberDao.getPhoneNumberByValue(phoneNumber);
    }

    @Override
    public boolean checkIfExistsByValue(String phoneNumberValue) {
        return phoneNumberDao.checkIfExistsByValue(phoneNumberValue) == 1;
    }
}
