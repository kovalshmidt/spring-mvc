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
        phoneNumber.setPhoneNumberValue(rs.getString("phoneNumberValue"));
        phoneNumber.setPhoneUserId(rs.getInt("phoneUser_id"));
        phoneNumber.setPhoneCompanyId(rs.getInt("phoneCompany_id"));
        return phoneNumber;
    }
}
