package com.epam.springmvc.dao;

import com.epam.springmvc.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    List<User> findAll();

    User getById(int id);

    void deleteById(int id);

    void update(User user);

    int save(User user);

    User findByEmail(String email);

    int checkIfUserExistsByNameAndSurname(String name, String surname);

}
