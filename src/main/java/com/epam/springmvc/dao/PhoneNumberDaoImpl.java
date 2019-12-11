package com.epam.springmvc.dao;

import com.epam.springmvc.mapper.PhoneNumberMapper;
import com.epam.springmvc.model.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

@Slf4j
@Repository
public class PhoneNumberDaoImpl implements PhoneNumberDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PhoneNumberDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PhoneNumber> findAll() {
        String sql = "SELECT * FROM phoneNumber";
        return jdbcTemplate.query(sql, new PhoneNumberMapper());
    }

    @Override
    public PhoneNumber getById(int id) {
        String sql = "SELECT * FROM phoneNumber WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new PhoneNumberMapper(), id);
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM phoneNumber WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(PhoneNumber phoneNumber) {
        String sql = "UPDATE phoneNumber SET phoneNumberValue = ?, phoneUser_id = ?, phoneCompany_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, phoneNumber.getPhoneNumberValue(), phoneNumber.getPhoneUserId(), phoneNumber.getPhoneCompanyId(),
                phoneNumber.getId());
    }

    @Override
    public int save(PhoneNumber phoneNumber) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO phoneNumber (phoneNumberValue, phoneUser_id, phoneCompany_id) VALUES (?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, phoneNumber.getPhoneNumberValue());
            ps.setLong(2, phoneNumber.getPhoneUserId());
            ps.setLong(3, phoneNumber.getPhoneCompanyId());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public int getUserIdByPhoneNumber(String phoneNumberValue) {
        String sql = "SELECT phoneUser_id FROM phoneNumber WHERE phoneNumberValue = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, phoneNumberValue);
    }

    @Override
    public Set<String> getPhoneNumberValuesByUserId(int userId) {
        String sql = "SELECT phoneNumberValue FROM phoneNumber WHERE phoneUser_id = ?";
        return jdbcTemplate.queryForObject(sql, Set.class, userId);
    }

    @Override
    public List<PhoneNumber> getPhoneNumberByPhoneUserId(int userPhoneId) {
        String sql = "SELECT * FROM phoneNumber WHERE phoneUser_id = ?";
        return jdbcTemplate.query(sql, new PhoneNumberMapper(), userPhoneId);
    }

    @Override
    public PhoneNumber getPhoneNumberByValue(String phoneNumberValue) {
        String sql = "SELECT * FROM phoneNumber WHERE phoneNumberValue = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new PhoneNumberMapper(), phoneNumberValue);
        } catch (EmptyResultDataAccessException e) {
            log.warn("No rows found with such phone number value: " + phoneNumberValue);
            return null;
        }
    }

    @Override
    public int checkIfExistsByValue(String phoneNumberValue) {
        String sql = "SELECT COUNT(1) FROM phoneNumber WHERE phoneNumberValue = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, phoneNumberValue);
    }
}
