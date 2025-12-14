package com.simple.atm.entity;

import java.io.Serializable;
import java.util.Date;

public class ChargeRecorder implements Serializable {
    private static final long serialVersionUID = 1L;
    private String cardNumber;
    private Double amount;
    private String type;
    private Date time;

    public ChargeRecorder() {}

    public ChargeRecorder(String cardNumber, Double amount, String type, Date time) {
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.type = type;
        this.time = time;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
