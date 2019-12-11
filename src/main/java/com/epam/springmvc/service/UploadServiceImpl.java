package com.epam.springmvc.service;

import com.epam.springmvc.model.PhoneCompany;
import com.epam.springmvc.model.PhoneUserAccount;
import com.epam.springmvc.model.User;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    private PhoneUserAccountService phoneUserAccountService;
    private PhoneCompanyService phoneCompanyService;
    private UserService userService;

    @Autowired
    public UploadServiceImpl(PhoneUserAccountService phoneUserAccountService, PhoneCompanyService phoneCompanyService,
                             UserService userService) {
        this.phoneUserAccountService = phoneUserAccountService;
        this.phoneCompanyService = phoneCompanyService;
        this.userService = userService;
    }

    public boolean uploadFileFromJson(MultipartFile file) {
        JSONParser jsonParser = new JSONParser();

        try (InputStreamReader reader = new InputStreamReader(file.getInputStream())) {

            JSONObject json = (JSONObject) jsonParser.parse(reader);
            JSONArray usersArray = (JSONArray) json.get("phoneDirectory");

            for (JSONObject userObject : (Iterable<JSONObject>) usersArray) {
                JSONObject phoneInfoObject = (JSONObject) userObject.get("phoneNumbersInfo");
                Set<String> keys = phoneInfoObject.keySet();

                //Create and save User
                User user = new User();
                String name = (String) userObject.get("name");
                String surname = (String) userObject.get("surname");
                user.setName(name);
                user.setSurname(surname);
                int tempUserId = userService.checkIfUserExistsByNameAndSurname(name, surname);
                int userId = tempUserId != 0 ? tempUserId : userService.save(user);

                for (String key : keys) {

                    if (phoneUserAccountService.checkIfExistsByValue(key)) {
                        log.warn("Phone number: " + key + " already exists");
                        log.warn("Phone number: " + key + " of User: " + name + " " + surname + " was not added");
                        continue;
                    }

                    //Create and save PhoneCompany
                    PhoneCompany phoneCompany = new PhoneCompany();
                    phoneCompany.setCompanyName((String) phoneInfoObject.get(key));
                    int companyId = phoneCompanyService.save(phoneCompany);

                    //Create and save PhoneUserAccount
                    PhoneUserAccount phoneUserAccount = new PhoneUserAccount();
                    phoneUserAccount.setPhoneNumber(key);
                    phoneUserAccount.setPhoneUserId(userId);
                    phoneUserAccount.setPhoneCompanyId(companyId);
                    phoneUserAccountService.save(phoneUserAccount);
                }
            }

            return true;
        } catch (IOException | ParseException e) {
            return false;
        }
    }

}
