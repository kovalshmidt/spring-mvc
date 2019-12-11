package com.epam.springmvc.dao;

import com.epam.springmvc.mapper.PhoneUserAccountMapper;
import com.epam.springmvc.model.PhoneUserAccount;
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
public class PhoneUserAccountDaoImpl implements PhoneUserAccountDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PhoneUserAccountDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PhoneUserAccount> findAll() {
        String sql = "SELECT * FROM phoneUserAccount";
        return jdbcTemplate.query(sql, new PhoneUserAccountMapper());
    }

    @Override
    public PhoneUserAccount getById(int id) {
        String sql = "SELECT * FROM phoneUserAccount WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new PhoneUserAccountMapper(), id);
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM phoneUserAccount WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(PhoneUserAccount phoneUserAccount) {
        String sql = "UPDATE phoneUserAccount SET phoneNumber = ?, user_id = ?, phoneCompany_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, phoneUserAccount.getPhoneNumber(), phoneUserAccount.getPhoneUserId(), phoneUserAccount.getPhoneCompanyId(),
                phoneUserAccount.getId());
    }

    @Override
    public int save(PhoneUserAccount phoneUserAccount) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO phoneUserAccount (phoneNumber, user_id, phoneCompany_id) VALUES (?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, phoneUserAccount.getPhoneNumber());
            ps.setLong(2, phoneUserAccount.getPhoneUserId());
            ps.setLong(3, phoneUserAccount.getPhoneCompanyId());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public int getUserIdByPhoneNumber(String phoneNumberValue) {
        String sql = "SELECT user_id FROM phoneUserAccount WHERE phoneNumber = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, phoneNumberValue);
    }

    @Override
    public Set<String> getPhoneNumberValuesByUserId(int userId) {
        String sql = "SELECT phoneNumber FROM phoneUserAccount WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, Set.class, userId);
    }

    @Override
    public List<PhoneUserAccount> getPhoneNumberByPhoneUserId(int userPhoneId) {
        String sql = "SELECT * FROM phoneUserAccount WHERE user_id = ?";
        return jdbcTemplate.query(sql, new PhoneUserAccountMapper(), userPhoneId);
    }

    @Override
    public PhoneUserAccount getPhoneNumberByValue(String phoneNumberValue) {
        String sql = "SELECT * FROM phoneUserAccount WHERE phoneNumber = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new PhoneUserAccountMapper(), phoneNumberValue);
        } catch (EmptyResultDataAccessException e) {
            log.warn("No rows found with such phone number value: " + phoneNumberValue);
            return null;
        }
    }

    @Override
    public int checkIfExistsByValue(String phoneNumberValue) {
        String sql = "SELECT COUNT(1) FROM phoneUserAccount WHERE phoneNumber = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, phoneNumberValue);
    }

    @Override
    public int getNumberOfPhonesNumbersByUserId(int id) {
        String sql = "SELECT COUNT(*) FROM phoneUserAccount WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, id);
    }
}
