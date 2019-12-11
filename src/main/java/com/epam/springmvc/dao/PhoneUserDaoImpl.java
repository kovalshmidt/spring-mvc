package com.epam.springmvc.dao;

import com.epam.springmvc.mapper.PhoneUserMapper;
import com.epam.springmvc.model.PhoneUser;
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
public class PhoneUserDaoImpl implements PhoneUserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PhoneUserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PhoneUser> findAll() {
        String sql = "SELECT * FROM phoneUser";
        return jdbcTemplate.query(sql, new PhoneUserMapper());
    }

    @Override
    public PhoneUser getById(int id) {
        String sql = "SELECT * FROM phoneUser WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new PhoneUserMapper(), id);
        } catch (
                EmptyResultDataAccessException e) {
            System.out.println("No rows found with such user id: " + id);
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM phoneUser WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(PhoneUser phoneUser) {
        String sql = "UPDATE phoneUser SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, phoneUser.getFullName(), phoneUser.getId());
    }

    @Override
    public int save(PhoneUser phoneUser) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO phoneUser (fullName) VALUES (?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, phoneUser.getFullName());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public int getNumberOfPhonesNumbersById(int id) {
        String sql = "SELECT COUNT(*) FROM phoneUserAccount WHERE phoneUser_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No phone numbers of user with id: " + id);
            return 0;
        }
    }

    @Override
    public int checkIfUserExistsByFullName(String fullname) {
        String sql = "SELECT id FROM phoneUser WHERE fullname = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, fullname);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No user found with fullname: " + fullname);
            return 0;
        }
    }
}
