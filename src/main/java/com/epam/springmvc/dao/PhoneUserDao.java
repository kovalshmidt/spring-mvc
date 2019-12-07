package com.epam.springmvc.dao;

import com.epam.springmvc.model.PhoneUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneUserDao {

    List<PhoneUser> findAll();

    PhoneUser getById(int id);

    void deleteById(int id);

    void update(PhoneUser phoneUser);

    int save(PhoneUser phoneUser);

    int getNumberOfPhonesNumbersById(int id);

    int checkIfUserExistsByFullName(String fullname);
}
