package com.epam.springmvc.service;

import com.epam.springmvc.dao.PhoneUserAccountDao;
import com.epam.springmvc.model.PhoneCompany;
import com.epam.springmvc.model.PhoneUserAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Slf4j
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
    @Transactional(rollbackFor = {Exception.class})
    public void changeMobileOperator(String phoneNumber, String newOperator) {
        PhoneUserAccount phoneUserAccount = getPhoneUserAccountByPhoneNumber(phoneNumber);
        int balance = phoneUserAccount.getAmount() - CHANGE_OPERATOR_PRICE;

        //Proceed with the operation only if there are enough funds to pay the operator transition
        if (balance < 0) {
            log.warn("Not enough funds to perform phone operator transition");
            return;
        }

        PhoneCompany phoneCompany = phoneCompanyService.getByCompanyName(newOperator);
        int phoneCompanyId = phoneCompany.getId();

        //Proceed with the operation only if the new operator does not correspond to the old one
        if (phoneCompanyId == phoneUserAccount.getPhoneCompanyId()) {
            log.warn("The new phone operator is equal to the old one, the transition cannot be performed");
            return;
        }

        phoneUserAccount.setPhoneCompanyId(phoneCompanyId);
        phoneUserAccount.setAmount(balance);

        phoneUserAccountDao.update(phoneUserAccount);
    }
}
