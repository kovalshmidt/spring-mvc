package com.epam.springmvc.mapper;

import com.epam.springmvc.model.PhoneUserAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneUserAccountMapper implements RowMapper<PhoneUserAccount> {

    @Override
    public PhoneUserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
        PhoneUserAccount phoneUserAccount = new PhoneUserAccount();
        phoneUserAccount.setId(rs.getInt("id"));
        phoneUserAccount.setPhoneNumber(rs.getString("phoneNumber"));
        phoneUserAccount.setAmount(rs.getInt("amount"));
        phoneUserAccount.setUserId(rs.getInt("user_id"));
        phoneUserAccount.setPhoneCompanyId(rs.getInt("phoneCompany_id"));
        return phoneUserAccount;
    }
}
