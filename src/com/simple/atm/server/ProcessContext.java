package com.simple.atm.server;

import com.simple.atm.dao.ChargeDao;
import com.simple.atm.dao.LogDao;
import com.simple.atm.dao.LoginDao;
import com.simple.atm.service.ATMService;
import com.simple.atm.service.LoginService;

public class ProcessContext {
    private final LoginDao loginDao;
    private final ChargeDao chargeDao;
    private final LogDao logDao;
    private final LoginService loginService;
    private final ATMService atmService;

    public ProcessContext(LoginDao loginDao, ChargeDao chargeDao, LogDao logDao) {
        this.loginDao = loginDao;
        this.chargeDao = chargeDao;
        this.logDao = logDao;
        this.loginService = new LoginService(loginDao, logDao);
        this.atmService = new ATMService(loginDao, chargeDao);
    }

    public LoginDao getLoginDao() {
        return loginDao;
    }

    public ChargeDao getChargeDao() {
        return chargeDao;
    }

    public LogDao getLogDao() {
        return logDao;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public ATMService getAtmService() {
        return atmService;
    }
}
