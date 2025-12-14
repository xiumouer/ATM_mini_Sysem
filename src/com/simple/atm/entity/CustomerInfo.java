package com.simple.atm.entity;

import java.io.Serializable;

public class CustomerInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String cardNumber;
    private String password;
    private String name;
    private Double balance;

    public CustomerInfo() {}

    public CustomerInfo(String cardNumber, String password, String name, Double balance) {
        this.cardNumber = cardNumber;
        this.password = password;
        this.name = name;
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
