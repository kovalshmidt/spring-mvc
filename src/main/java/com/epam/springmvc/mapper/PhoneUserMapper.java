package com.epam.springmvc.mapper;

import com.epam.springmvc.model.PhoneUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneUserMapper implements RowMapper<PhoneUser> {

    @Override
    public PhoneUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        PhoneUser phoneUser = new PhoneUser();
        phoneUser.setId(rs.getInt("id"));
        phoneUser.setFullName(rs.getString("fullName"));
        return phoneUser;
    }
}
