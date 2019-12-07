package com.epam.springmvc.service;

import com.epam.springmvc.model.PhoneCompany;
import com.epam.springmvc.model.PhoneNumber;
import com.epam.springmvc.model.PhoneUser;
import com.epam.springmvc.model.PhoneUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PhoneUserInfoServiceImpl implements PhoneUserInfoService {

    private PhoneUserService phoneUserService;
    private PhoneNumberService phoneNumberService;
    private PhoneCompanyService phoneCompanyService;

    @Autowired
    public PhoneUserInfoServiceImpl(PhoneUserService phoneUserService, PhoneNumberService phoneNumberService, PhoneCompanyService phoneCompanyService) {
        this.phoneUserService = phoneUserService;
        this.phoneNumberService = phoneNumberService;
        this.phoneCompanyService = phoneCompanyService;
    }

    @Override
    public void save(PhoneUserInfo phoneUserInfo) {

        PhoneUser phoneUser = new PhoneUser(phoneUserInfo.getFullName());
        int userId = phoneUserService.save(phoneUser);

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
                String phoneNumberValue = phoneNumber.getPhoneNumberValue();
                String companyName = phoneCompanyService.getById(phoneNumber.getPhoneCompanyId()).getCompanyName();
                phoneInfo.put(phoneNumberValue, companyName);
            }
            phoneUserInfo.setPhoneInfo(phoneInfo);
        }

        PhoneUser phoneUser = Optional.ofNullable(phoneUserService.getById(id)).orElse(new PhoneUser(""));
        phoneUserInfo.setUserId(phoneUser.getId());
        phoneUserInfo.setFullName(phoneUser.getFullName());

        return phoneUserInfo;
    }
}
