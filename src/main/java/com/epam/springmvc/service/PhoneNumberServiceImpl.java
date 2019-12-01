package com.epam.springmvc.service;

import com.epam.springmvc.dao.PhoneNumberDao;
import com.epam.springmvc.model.PhoneNumber;
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
    public List<PhoneNumber> findAll() {
        return phoneNumberDao.findAll();
    }

    @Override
    public PhoneNumber getById(int id) {
        return phoneNumberDao.getById(id);
    }

    @Override
    public void deleteById(int id) {
        phoneNumberDao.deleteById(id);
    }

    @Override
    public void update(PhoneNumber phoneNumber) {
        phoneNumberDao.update(phoneNumber);
    }

    @Override
    public int save(PhoneNumber phoneNumber) {
        return phoneNumberDao.save(phoneNumber);
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
    public List<PhoneNumber> getPhoneNumbersByUserId(int userId) {
        return phoneNumberDao.getPhoneNumberByUserId(userId);
    }
}
