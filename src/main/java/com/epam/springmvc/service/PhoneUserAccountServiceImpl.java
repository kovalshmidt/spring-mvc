package com.epam.springmvc.service;

import com.epam.springmvc.dao.PhoneUserAccountDao;
import com.epam.springmvc.model.PhoneUserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PhoneUserAccountServiceImpl implements PhoneUserAccountService {

    private PhoneUserAccountDao phoneUserAccountDao;

    @Autowired
    public PhoneUserAccountServiceImpl(PhoneUserAccountDao phoneUserAccountDao) {
        this.phoneUserAccountDao = phoneUserAccountDao;
    }

    @Override
    public List<PhoneUserAccount> findAll() {
        return phoneUserAccountDao.findAll();
    }

    @Override
    public PhoneUserAccount getById(int id) {
        return phoneUserAccountDao.getById(id);
    }

    @Override
    public void deleteById(int id) {
        phoneUserAccountDao.deleteById(id);
    }

    @Override
    public void update(PhoneUserAccount phoneUserAccount) {
        phoneUserAccountDao.update(phoneUserAccount);
    }

    @Override
    public int save(PhoneUserAccount phoneUserAccount) {
        return phoneUserAccountDao.save(phoneUserAccount);
    }

    @Override
    public int getUserIdByPhoneNumber(String phoneNumberValue) {
        return phoneUserAccountDao.getUserIdByPhoneNumber(phoneNumberValue);
    }

    @Override
    public Set<String> getPhoneNumberValuesByUserId(int userId) {
        return phoneUserAccountDao.getPhoneNumberValuesByUserId(userId);
    }

    @Override
    public List<PhoneUserAccount> getPhoneNumbersByPhoneUserId(int userId) {
        return phoneUserAccountDao.getPhoneNumberByPhoneUserId(userId);
    }

    @Override
    public PhoneUserAccount getPhoneNumberByValue(String phoneNumber) {
        return phoneUserAccountDao.getPhoneNumberByValue(phoneNumber);
    }

    @Override
    public boolean checkIfExistsByValue(String phoneNumberValue) {
        return phoneUserAccountDao.checkIfExistsByValue(phoneNumberValue) == 1;
    }

    @Override
    public int getNumberOfPhonesNumbersByUserId(int id) {
        return phoneUserAccountDao.getNumberOfPhonesNumbersByUserId(id);
    }
}
