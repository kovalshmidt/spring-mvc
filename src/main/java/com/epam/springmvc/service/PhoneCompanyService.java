package com.epam.springmvc.service;

import com.epam.springmvc.model.PhoneCompany;

import java.util.List;

public interface PhoneCompanyService {

    List<PhoneCompany> findAll();

    PhoneCompany getById(int id);

    void deleteById(int id);

    void update(PhoneCompany phoneCompany);

    int save(PhoneCompany phoneCompany);

    PhoneCompany getByCompanyName(String companyName);
}
