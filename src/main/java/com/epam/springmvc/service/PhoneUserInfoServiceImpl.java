package com.epam.springmvc.service;

import com.epam.springmvc.model.*;
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
    private UserService userService;

    @Autowired
    public PhoneUserInfoServiceImpl(PhoneUserService phoneUserService, PhoneNumberService phoneNumberService,
                                    PhoneCompanyService phoneCompanyService, UserService userService) {
        this.phoneUserService = phoneUserService;
        this.phoneNumberService = phoneNumberService;
        this.phoneCompanyService = phoneCompanyService;
        this.userService = userService;
    }

    @Override
    public void save(PhoneUserInfo phoneUserInfo) {

        PhoneUser phoneUser = new PhoneUser(phoneUserInfo.getFullName());
        int userId = phoneUserService.save(phoneUser);

        for (Map.Entry<String, String> entry : phoneUserInfo.getPhoneInfo().entrySet()) {

            PhoneCompany phoneCompany = new PhoneCompany(entry.getKey());
            int phoneCompanyId = phoneCompanyService.save(phoneCompany);

            PhoneUserAccount phoneUserAccount = new PhoneUserAccount(entry.getValue(), userId, phoneCompanyId);
            phoneNumberService.save(phoneUserAccount);
        }
    }

    @Override
    public PhoneUserInfo createByPhoneUserId(int id) {
        PhoneUserInfo phoneUserInfo = new PhoneUserInfo();

        //Retrieve the PhoneNumbers
        List<PhoneUserAccount> phoneUserAccounts = phoneNumberService.getPhoneNumbersByPhoneUserId(id);
        Map<String, String> phoneInfo = new HashMap<>();
        if (phoneUserAccounts.isEmpty()) {
            phoneUserInfo.setPhoneInfo(phoneInfo);
        } else {
            //Populate the phoneInfo map with 'key:phoneNumber' and 'value:companyName'
            for (PhoneUserAccount phoneUserAccount : phoneUserAccounts) {
                String phoneNumberValue = phoneUserAccount.getPhoneNumber();
                String companyName = phoneCompanyService.getById(phoneUserAccount.getPhoneCompanyId()).getCompanyName();
                phoneInfo.put(phoneNumberValue, companyName);
            }
            phoneUserInfo.setPhoneInfo(phoneInfo);
        }

        //Find PhoneUser and set to PhoneUserInfo 'phoneUserId' and 'fullName'
        PhoneUser phoneUser = Optional.ofNullable(phoneUserService.getById(id)).orElse(new PhoneUser(""));
        phoneUserInfo.setPhoneUserId(phoneUser.getId());
        phoneUserInfo.setFullName(phoneUser.getFullName());

        return phoneUserInfo;
    }

    @Override
    public PhoneUserInfo createByUserId(int id) {

        //Find User and set to PhoneUserInfo data from him
        User user = userService.getById(id);
        if (user == null) {
            return new PhoneUserInfo();
        }

        //Get PhoneUserInfo and set data 'email' and 'roles' from User
        int phoneUserId = user.getPhoneUserId();
        PhoneUserInfo phoneUserInfo = createByPhoneUserId(phoneUserId);
        phoneUserInfo.setEmail(user.getEmail());
        phoneUserInfo.setRoles(user.getRoles());

        return phoneUserInfo;
    }
}
