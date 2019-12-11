package com.epam.springmvc.dao;

import com.epam.springmvc.mapper.UserMapper;
import com.epam.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public User getById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
        } catch (
                EmptyResultDataAccessException e) {
            System.out.println("No rows found with such user id: " + id);
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE user SET name = ? surname = ? email = ? password = ? roles = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getRoles(), user.getId());
    }

    @Override
    public int save(User user) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO users (name, surname, email, password, roles) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRoles());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), email);
        } catch (
                EmptyResultDataAccessException e) {
            System.out.println("No rows found with such user email: " + email);
            return null;
        }
    }

    @Override
    public User findByPhoneUserId(int id) {
        String sql = "SELECT * FROM users WHERE phoneUser_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
        } catch (
                EmptyResultDataAccessException e) {
            System.out.println("No rows found with such user phone id: " + id);
            return null;
        }
    }
}
