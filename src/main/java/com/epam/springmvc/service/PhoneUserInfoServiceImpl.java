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

        for (Map.Entry<String, String> entry : phoneUserInfo.getPhoneInfo().entrySet()) {

            PhoneCompany phoneCompany = new PhoneCompany(entry.getKey());
            int phoneCompanyId = phoneCompanyService.save(phoneCompany);

            PhoneUserAccount phoneUserAccount = new PhoneUserAccount(entry.getValue(), userId, phoneCompanyId);
            phoneUserAccountService.save(phoneUserAccount);
        }
    }

    @Override
    public PhoneUserInfo createByUserId(int id) {
        PhoneUserInfo phoneUserInfo = new PhoneUserInfo();

        //Retrieve the PhoneNumbers
        List<PhoneUserAccount> phoneUserAccounts = phoneUserAccountService.getPhoneNumbersByPhoneUserId(id);
        Map<String, String> phoneInfo = new HashMap<>();
        if (phoneUserAccounts.isEmpty()) {
            phoneUserInfo.setPhoneInfo(phoneInfo);
        } else {
            //Populate the phoneInfo map with 'key:phoneNumber' and 'value:companyName'
            for (PhoneUserAccount phoneUserAccount : phoneUserAccounts) {
                String phoneNumber = phoneUserAccount.getPhoneNumber();
                String companyName = phoneCompanyService.getById(phoneUserAccount.getPhoneCompanyId()).getCompanyName();
                phoneInfo.put(phoneNumber, companyName);
            }
            phoneUserInfo.setPhoneInfo(phoneInfo);
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
