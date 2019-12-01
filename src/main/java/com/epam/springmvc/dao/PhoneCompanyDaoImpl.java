package com.epam.springmvc.dao;

import com.epam.springmvc.mapper.PhoneCompanyMapper;
import com.epam.springmvc.model.PhoneCompany;
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
public class PhoneCompanyDaoImpl implements PhoneCompanyDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PhoneCompanyDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PhoneCompany> findAll() {
        String sql = "SELECT * FROM phoneCompany";
        return jdbcTemplate.query(sql, new PhoneCompanyMapper());
    }

    @Override
    public PhoneCompany getById(int id) {
        String sql = "SELECT * FROM phoneCompany WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new PhoneCompanyMapper(), id);
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM phoneCompany WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(PhoneCompany phoneCompany) {
        String sql = "UPDATE phoneCompany SET companyName = ? WHERE id = ?";
        jdbcTemplate.update(sql, phoneCompany.getCompanyName(), phoneCompany.getId());
    }

    @Override
    public int save(PhoneCompany phoneCompany) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO phoneCompany (companyName) VALUES (?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, phoneCompany.getCompanyName());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public PhoneCompany getByCompanyName(String companyName) {
        String sql = "SELECT * FROM phoneCompany WHERE companyName = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new PhoneCompanyMapper(), companyName);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No rows found with such company name: " + companyName);
            return null;
        }
    }
}
