package com.epam.springmvc.service;

import com.epam.springmvc.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PhoneUserInfoServiceImpl implements PhoneUserInfoService {
    private PhoneUserAccountService phoneUserAccountService;
    private PhoneCompanyService phoneCompanyService;
    private UserService userService;

    @Autowired
    public PhoneUserInfoServiceImpl(PhoneUserAccountService phoneUserAccountService,
                                    PhoneCompanyService phoneCompanyService, UserService userService) {
        this.phoneUserAccountService = phoneUserAccountService;
        this.phoneCompanyService = phoneCompanyService;
        this.userService = userService;
    }

    @Override
    public void save(PhoneUserInfo phoneUserInfo) {

        User phoneUser = new User(phoneUserInfo.getName(), phoneUserInfo.getSurname());
        int userId = userService.save(phoneUser);

        for (PhoneUserInfo.PhoneInfo phoneInfo : phoneUserInfo.getPhoneInfoSet()) {
            PhoneCompany phoneCompany = new PhoneCompany(phoneInfo.getPhoneCompany());
            int phoneCompanyId = phoneCompanyService.save(phoneCompany);

            PhoneUserAccount phoneUserAccount = new PhoneUserAccount(phoneInfo.getPhoneNumber(), phoneInfo.getBalance(),
                    userId, phoneCompanyId);

            phoneUserAccountService.save(phoneUserAccount);
        }
    }

    @Override
    public PhoneUserInfo createByUserId(int id) {
        PhoneUserInfo phoneUserInfo = new PhoneUserInfo();

        //Retrieve the PhoneNumbers
        List<PhoneUserAccount> phoneUserAccounts = phoneUserAccountService.getPhoneNumbersByPhoneUserId(id);
        Set<PhoneUserInfo.PhoneInfo> phoneInfos = new HashSet<>();
        if (phoneUserAccounts.isEmpty()) {
            phoneUserInfo.setPhoneInfoSet(phoneInfos);
        } else {
            //Populate the phoneInfo map with 'key:phoneNumber' and 'value:companyName'
            for (PhoneUserAccount phoneUserAccount : phoneUserAccounts) {
                String phoneNumber = phoneUserAccount.getPhoneNumber();
                String companyName = phoneCompanyService.getById(phoneUserAccount.getPhoneCompanyId()).getCompanyName();
                int balance = phoneUserAccount.getAmount();
                phoneInfos.add(new PhoneUserInfo.PhoneInfo(phoneNumber, companyName, balance));
            }
            phoneUserInfo.setPhoneInfoSet(phoneInfos);
        }

        //Find PhoneUser and set to PhoneUserInfo 'userId' and 'name' 'surname' 'email' and 'roles'
        User user = Optional.ofNullable(userService.getById(id)).orElse(new User("", ""));
        phoneUserInfo.setPhoneUserId(user.getId());
        phoneUserInfo.setName(user.getName());
        phoneUserInfo.setSurname(user.getSurname());
        phoneUserInfo.setEmail(user.getEmail());
        phoneUserInfo.setRoles(user.getRoles());

        return phoneUserInfo;
    }
}
