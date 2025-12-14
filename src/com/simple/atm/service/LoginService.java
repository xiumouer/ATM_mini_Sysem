package com.simple.atm.service;

import com.simple.atm.dao.LogDao;
import com.simple.atm.dao.LoginDao;
import com.simple.atm.entity.CustomerInfo;
import com.simple.atm.entity.LogInfo;

import java.util.Date;

public class LoginService {
    private final LoginDao loginDao;
    private final LogDao logDao;

    public LoginService(LoginDao loginDao, LogDao logDao) {
        this.loginDao = loginDao;
        this.logDao = logDao;
    }

    public boolean login(String card, String password) {
        CustomerInfo info = loginDao.findByCard(card);
        boolean ok = info != null && password != null && password.equals(info.getPassword());
        if (ok) {
            logDao.add(new LogInfo(card, "login", new Date()));
        }
        return ok;
    }

    public void logout(String card) {
        logDao.add(new LogInfo(card, "logout", new Date()));
    }
}
