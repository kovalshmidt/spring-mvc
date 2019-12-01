package com.epam.springmvc.service;

import com.epam.springmvc.model.PhoneCompany;
import com.epam.springmvc.model.PhoneNumber;
import com.epam.springmvc.model.PhoneUserInfo;
import com.epam.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PhoneUserInfoServiceImpl implements PhoneUserInfoService {

    private UserService userService;
    private PhoneNumberService phoneNumberService;
    private PhoneCompanyService phoneCompanyService;

    @Autowired
    public PhoneUserInfoServiceImpl(UserService userService, PhoneNumberService phoneNumberService, PhoneCompanyService phoneCompanyService) {
        this.userService = userService;
        this.phoneNumberService = phoneNumberService;
        this.phoneCompanyService = phoneCompanyService;
    }

    @Override
    public void save(PhoneUserInfo phoneUserInfo) {

        User user = new User(phoneUserInfo.getFullName());
        int userId = userService.save(user);

        for (Map.Entry<String, String> entry : phoneUserInfo.getPhoneInfo().entrySet()) {

            PhoneCompany phoneCompany = new PhoneCompany(entry.getKey());
            int phoneCompanyId = phoneCompanyService.save(phoneCompany);

            PhoneNumber phoneNumber = new PhoneNumber(entry.getValue(), userId, phoneCompanyId);
            phoneNumberService.save(phoneNumber);
        }
    }

    @Override
    public PhoneUserInfo createByUserId(int id) {
        PhoneUserInfo phoneUserInfo = new PhoneUserInfo();

        List<PhoneNumber> phoneNumbers = phoneNumberService.getPhoneNumbersByUserId(id);
        Map<String, String> phoneInfo = new HashMap<>();
        if (phoneNumbers.isEmpty()) {
            phoneUserInfo.setPhoneInfo(phoneInfo);
        } else {

            for (PhoneNumber phoneNumber : phoneNumbers) {
                String phoneNumberValue = phoneNumber.getPhoneNumber();
                String companyName = phoneCompanyService.getById(phoneNumber.getPhoneCompanyId()).getCompanyName();
                phoneInfo.put(phoneNumberValue, companyName);
            }
            phoneUserInfo.setPhoneInfo(phoneInfo);
        }

        User user = Optional.ofNullable(userService.getById(id)).orElse(new User(""));
        phoneUserInfo.setUserId(user.getId());
        phoneUserInfo.setFullName(user.getFullName());

        return phoneUserInfo;
    }
}
