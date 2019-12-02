package com.epam.springmvc.service;

import com.epam.springmvc.dao.UserDao;
import com.epam.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    public void deleteById(int id) {
        userDao.deleteById(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public int save(User user) {
        return userDao.save(user);
    }

    @Override
    public int getNumberOfPhonesNumbersById(int id) {
        return userDao.getNumberOfPhonesNumbersById(id);
    }

    @Override
    public int checkIfUserExistsByFullName(String fullname) {
        return userDao.checkIfUserExistsByFullName(fullname);
    }
}
