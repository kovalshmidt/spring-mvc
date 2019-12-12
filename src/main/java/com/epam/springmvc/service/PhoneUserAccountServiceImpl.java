package com.epam.springmvc.service;

import com.epam.springmvc.dao.PhoneUserAccountDao;
import com.epam.springmvc.model.PhoneCompany;
import com.epam.springmvc.model.PhoneUserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class PhoneUserAccountServiceImpl implements PhoneUserAccountService {

    private PhoneUserAccountDao phoneUserAccountDao;
    private PhoneCompanyService phoneCompanyService;

    @Autowired
    public PhoneUserAccountServiceImpl(PhoneUserAccountDao phoneUserAccountDao, PhoneCompanyService phoneCompanyService) {
        this.phoneUserAccountDao = phoneUserAccountDao;
        this.phoneCompanyService = phoneCompanyService;
    }

    @Override
    public List<PhoneUserAccount> findAll() {
        return phoneUserAccountDao.findAll();
    }

    @Override
    public PhoneUserAccount getById(int id) {
        return phoneUserAccountDao.getById(id);
    }

    @Override
    public void deleteById(int id) {
        phoneUserAccountDao.deleteById(id);
    }

    @Override
    public void update(PhoneUserAccount phoneUserAccount) {
        phoneUserAccountDao.update(phoneUserAccount);
    }

    @Override
    public int save(PhoneUserAccount phoneUserAccount) {
        return phoneUserAccountDao.save(phoneUserAccount);
    }

    @Override
    public int getUserIdByPhoneNumber(String phoneNumber) {
        return phoneUserAccountDao.getUserIdByPhoneNumber(phoneNumber);
    }

    @Override
    public Set<String> getPhoneNumberValuesByUserId(int userId) {
        return phoneUserAccountDao.getPhoneNumberValuesByUserId(userId);
    }

    @Override
    public List<PhoneUserAccount> getPhoneNumbersByPhoneUserId(int userId) {
        return phoneUserAccountDao.getPhoneUserAccountByUserId(userId);
    }

    @Override
    public PhoneUserAccount getPhoneUserAccountByPhoneNumber(String phoneNumber) {
        return phoneUserAccountDao.getPhoneUserAccountByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean checkIfExistsByValue(String phoneNumber) {
        return phoneUserAccountDao.checkIfExistsByPhoneNumber(phoneNumber) == 1;
    }

    @Override
    public int getNumberOfPhonesNumbersByUserId(int id) {
        return phoneUserAccountDao.getNumberOfPhonesNumbersByUserId(id);
    }

    private final int CHANGE_OPERATOR_PRICE = 10;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeMobileOperator(String phoneNumber, String newOperator) {
        PhoneUserAccount phoneUserAccount = getPhoneUserAccountByPhoneNumber(phoneNumber);
        //Check if there is enough money
        int balance = phoneUserAccount.getAmount() - CHANGE_OPERATOR_PRICE;
        if (balance >= 0) {

            //There is enough money, proceed with operator change
            PhoneCompany phoneCompany = phoneCompanyService.getByCompanyName(newOperator);
            phoneUserAccount.setPhoneCompanyId(phoneCompany.getId());
            //Set the new amount after operator change
            phoneUserAccount.setAmount(balance);

            phoneUserAccountDao.update(phoneUserAccount);
        }
    }
}
