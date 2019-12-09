package com.epam.springmvc.service;

import com.epam.springmvc.model.PhoneCompany;
import com.epam.springmvc.model.PhoneNumber;
import com.epam.springmvc.model.PhoneUser;
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

    private PhoneUserService phoneUserService;
    private PhoneNumberService phoneNumberService;
    private PhoneCompanyService phoneCompanyService;

    @Autowired
    public UploadServiceImpl(PhoneUserService phoneUserService, PhoneNumberService phoneNumberService, PhoneCompanyService phoneCompanyService) {
        this.phoneUserService = phoneUserService;
        this.phoneNumberService = phoneNumberService;
        this.phoneCompanyService = phoneCompanyService;
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
                PhoneUser phoneUser = new PhoneUser();
                String fullName = (String) userObject.get("fullName");
                phoneUser.setFullName(fullName);
                int tempUserId = phoneUserService.checkIfUserExistsByFullName(fullName);
                int userId = tempUserId != 0 ? tempUserId : phoneUserService.save(phoneUser);

                for (String key : keys) {

                    if (phoneNumberService.checkIfExistsByValue(key)) {
                        log.warn("Phone number: " + key + " already exists");
                        log.warn("Phone number: " + key + " of User: " + fullName + " was not added");
                        continue;
                    }

                    //Create and save PhoneCompany
                    PhoneCompany phoneCompany = new PhoneCompany();
                    phoneCompany.setCompanyName((String) phoneInfoObject.get(key));
                    int companyId = phoneCompanyService.save(phoneCompany);

                    //Create and save PhoneNumber
                    PhoneNumber phoneNumber = new PhoneNumber();
                    phoneNumber.setPhoneNumberValue(key);
                    phoneNumber.setPhoneUserId(userId);
                    phoneNumber.setPhoneCompanyId(companyId);
                    phoneNumberService.save(phoneNumber);
                }
            }

            return true;
        } catch (IOException | ParseException e) {
            return false;
        }
    }

}
