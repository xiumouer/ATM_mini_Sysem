package com.simple.atm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AccountQueryResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private String cardNumber;
    private String name;
    private Double balance;
    private List<String> logs = new ArrayList<String>();
    private boolean status;
    private String message;

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }
    public List<String> getLogs() { return logs; }
    public void setLogs(List<String> logs) { this.logs = logs; }
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
