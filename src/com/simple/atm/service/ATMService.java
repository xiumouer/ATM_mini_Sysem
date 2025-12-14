package com.simple.atm.service;

import com.simple.atm.dao.ChargeDao;
import com.simple.atm.dao.LoginDao;
import com.simple.atm.entity.ChargeRecorder;
import com.simple.atm.entity.CustomerInfo;

import java.util.Date;

public class ATMService {
    private final LoginDao loginDao;
    private final ChargeDao chargeDao;

    public ATMService(LoginDao loginDao, ChargeDao chargeDao) {
        this.loginDao = loginDao;
        this.chargeDao = chargeDao;
    }

    public boolean deposit(String card, Double amount) {
        if (amount == null || amount.doubleValue() <= 0) return false;
        CustomerInfo info = loginDao.findByCard(card);
        if (info == null) return false;
        info.setBalance(Double.valueOf(info.getBalance().doubleValue() + amount.doubleValue()));
        chargeDao.add(new ChargeRecorder(card, amount, "deposit", new Date()));
        return true;
    }

    public boolean withdraw(String card, Double amount) {
        if (amount == null || amount.doubleValue() <= 0) return false;
        CustomerInfo info = loginDao.findByCard(card);
        if (info == null) return false;
        if (info.getBalance().doubleValue() < amount.doubleValue()) return false;
        info.setBalance(Double.valueOf(info.getBalance().doubleValue() - amount.doubleValue()));
        chargeDao.add(new ChargeRecorder(card, amount, "withdraw", new Date()));
        return true;
    }

    public boolean transfer(String sourceCard, String targetCard, Double amount) {
        if (amount == null || amount.doubleValue() <= 0) return false;
        CustomerInfo source = loginDao.findByCard(sourceCard);
        CustomerInfo target = loginDao.findByCard(targetCard);
        if (source == null || target == null) return false;
        if (source.getBalance().doubleValue() < amount.doubleValue()) return false;
        source.setBalance(Double.valueOf(source.getBalance().doubleValue() - amount.doubleValue()));
        target.setBalance(Double.valueOf(target.getBalance().doubleValue() + amount.doubleValue()));
        chargeDao.add(new ChargeRecorder(sourceCard, amount, "transfer", new Date()));
        return true;
    }
}
