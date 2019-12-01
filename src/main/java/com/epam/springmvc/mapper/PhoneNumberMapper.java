package com.epam.springmvc.mapper;

import com.epam.springmvc.model.PhoneNumber;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneNumberMapper implements RowMapper<PhoneNumber> {

    @Override
    public PhoneNumber mapRow(ResultSet rs, int rowNum) throws SQLException {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setId(rs.getInt("id"));
        phoneNumber.setPhoneNumber(rs.getString("phoneNumber"));
        phoneNumber.setUserId(rs.getInt("users_id"));
        phoneNumber.setPhoneCompanyId(rs.getInt("phoneCompany_id"));
        return phoneNumber;
    }
}
