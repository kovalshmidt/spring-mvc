package com.epam.springmvc.service;

import com.epam.springmvc.model.PhoneCompany;
import com.epam.springmvc.model.PhoneNumber;
import com.epam.springmvc.model.User;
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

@Service
public class UploadServiceImpl implements UploadService {

    private UserService userService;
    private PhoneNumberService phoneNumberService;
    private PhoneCompanyService phoneCompanyService;

    @Autowired
    public UploadServiceImpl(UserService userService, PhoneNumberService phoneNumberService, PhoneCompanyService phoneCompanyService) {
        this.userService = userService;
        this.phoneNumberService = phoneNumberService;
        this.phoneCompanyService = phoneCompanyService;
    }

    public boolean uploadFileFromJson(MultipartFile file) {
        JSONParser jsonParser = new JSONParser();

        try (InputStreamReader reader = new InputStreamReader(file.getInputStream())) {

            JSONObject json = (JSONObject) jsonParser.parse(reader);
            JSONArray usersArray = (JSONArray) json.get("phoneDirectory");

            for (JSONObject userObject : (Iterable<JSONObject>) usersArray) {
                User user = new User();
                user.setFullName((String) userObject.get("fullName"));
                int userId = userService.save(user);

                JSONObject phoneInfoObject = (JSONObject) userObject.get("phoneNumbersInfo");
                Set<String> keys = phoneInfoObject.keySet();

                for (String key : keys) {
                    PhoneCompany phoneCompany = new PhoneCompany();
                    phoneCompany.setCompanyName((String) phoneInfoObject.get(key));
                    int companyId = phoneCompanyService.save(phoneCompany);

                    PhoneNumber phoneNumber = new PhoneNumber();
                    phoneNumber.setPhoneNumber(key);
                    phoneNumber.setUserId(userId);
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
