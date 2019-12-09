package com.epam.springmvc.service;

import com.epam.springmvc.dao.PhoneUserDao;
import com.epam.springmvc.model.PhoneUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneUserServiceImpl implements PhoneUserService {

    private PhoneUserDao phoneUserDao;

    @Autowired
    public PhoneUserServiceImpl(PhoneUserDao phoneUserDao) {
        this.phoneUserDao = phoneUserDao;
    }

    public List<PhoneUser> findAll() {
        return phoneUserDao.findAll();
    }

    @Override
    public PhoneUser getById(int id) {
        return phoneUserDao.getById(id);
    }

    @Override
    public void deleteById(int id) {
        phoneUserDao.deleteById(id);
    }

    @Override
    public void update(PhoneUser phoneUser) {
        phoneUserDao.update(phoneUser);
    }

    @Override
    public int save(PhoneUser phoneUser) {
        return phoneUserDao.save(phoneUser);
    }

    @Override
    public int getNumberOfPhonesNumbersById(int id) {
        return phoneUserDao.getNumberOfPhonesNumbersById(id);
    }

    @Override
    public int checkIfUserExistsByFullName(String fullname) {
        return phoneUserDao.checkIfUserExistsByFullName(fullname);
    }
}
