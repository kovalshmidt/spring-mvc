package com.epam.springmvc.service;

import com.epam.springmvc.dao.PhoneCompanyDao;
import com.epam.springmvc.model.PhoneCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneCompanyServiceImpl implements PhoneCompanyService {

    private final PhoneCompanyDao phoneCompanyDao;

    @Autowired
    public PhoneCompanyServiceImpl(PhoneCompanyDao phoneCompanyDao) {
        this.phoneCompanyDao = phoneCompanyDao;
    }

    @Override
    public List<PhoneCompany> findAll() {
        return phoneCompanyDao.findAll();
    }

    @Override
    public PhoneCompany getById(int id) {
        return phoneCompanyDao.getById(id);
    }

    @Override
    public void deleteById(int id) {
        phoneCompanyDao.getById(id);
    }

    @Override
    public void update(PhoneCompany phoneCompany) {
        phoneCompanyDao.update(phoneCompany);
    }

    @Override
    public int save(PhoneCompany phoneCompany) {
        PhoneCompany phoneCompanyDb = getByCompanyName(phoneCompany.getCompanyName());
        if (phoneCompanyDb == null) {
            return phoneCompanyDao.save(phoneCompany);
        }
        return phoneCompanyDb.getId();
    }

    @Override
    public PhoneCompany getByCompanyName(String companyName) {
        return phoneCompanyDao.getByCompanyName(companyName);
    }
}
