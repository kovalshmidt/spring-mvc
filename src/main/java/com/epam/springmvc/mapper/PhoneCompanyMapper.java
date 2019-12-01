package com.epam.springmvc.mapper;

import com.epam.springmvc.model.PhoneCompany;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneCompanyMapper implements RowMapper<PhoneCompany> {

    @Override
    public PhoneCompany mapRow(ResultSet rs, int rowNum) throws SQLException {
        PhoneCompany phoneCompany = new PhoneCompany();
        phoneCompany.setId(rs.getInt("id"));
        phoneCompany.setCompanyName(rs.getString("companyName"));
        return phoneCompany;
    }
}
