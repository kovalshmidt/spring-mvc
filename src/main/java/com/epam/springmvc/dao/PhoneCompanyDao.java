package com.epam.springmvc.dao;

import com.epam.springmvc.model.PhoneCompany;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneCompanyDao {

    List<PhoneCompany> findAll();

    PhoneCompany getById(int id);

    void deleteById(int id);

    void update(PhoneCompany phoneCompany);

    int save(PhoneCompany phoneCompany);

    PhoneCompany getByCompanyName(String companyName);

}
