package com.epam.springmvc.service;

import com.epam.springmvc.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User getById(int id);

    void deleteById(int id);

    void update(User user);

    int save(User user);

    User findByEmail(String email);

    User findByPhoneUserId(int id);
}
