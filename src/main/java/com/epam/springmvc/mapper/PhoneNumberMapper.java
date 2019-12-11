package com.epam.springmvc.mapper;

import com.epam.springmvc.model.PhoneUserAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneNumberMapper implements RowMapper<PhoneUserAccount> {

    @Override
    public PhoneUserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
        PhoneUserAccount phoneUserAccount = new PhoneUserAccount();
        phoneUserAccount.setId(rs.getInt("id"));
        phoneUserAccount.setPhoneNumber(rs.getString("phoneNumber"));
        phoneUserAccount.setPhoneUserId(rs.getInt("phoneUser_id"));
        phoneUserAccount.setPhoneCompanyId(rs.getInt("phoneCompany_id"));
        return phoneUserAccount;
    }
}
