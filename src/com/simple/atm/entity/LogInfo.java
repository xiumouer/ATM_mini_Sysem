package com.simple.atm.entity;

import java.io.Serializable;
import java.util.Date;

public class LogInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String cardNumber;
    private String action;
    private Date time;

    public LogInfo() {}

    public LogInfo(String cardNumber, String action, Date time) {
        this.cardNumber = cardNumber;
        this.action = action;
        this.time = time;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
