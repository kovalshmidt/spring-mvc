package com.epam.springmvc.service;

import com.epam.springmvc.model.PhoneUserInfo;

public interface PhoneUserInfoService {

    void save(PhoneUserInfo phoneUserInfo);

    PhoneUserInfo createByUserId(int id);
}
