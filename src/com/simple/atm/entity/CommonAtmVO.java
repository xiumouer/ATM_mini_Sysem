package com.simple.atm.entity;

import java.io.Serializable;

public class CommonAtmVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private CustomerInfo customerInfo;
    private Double operatorMoney;
    private String type;
    private String sourceCardNumber;
    private String targetCardNumber;

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public Double getOperatorMoney() {
        return operatorMoney;
    }

    public void setOperatorMoney(Double operatorMoney) {
        this.operatorMoney = operatorMoney;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSourceCardNumber() {
        return sourceCardNumber;
    }

    public void setSourceCardNumber(String sourceCardNumber) {
        this.sourceCardNumber = sourceCardNumber;
    }

    public String getTargetCardNumber() {
        return targetCardNumber;
    }

    public void setTargetCardNumber(String targetCardNumber) {
        this.targetCardNumber = targetCardNumber;
    }
}
