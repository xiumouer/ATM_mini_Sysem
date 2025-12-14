package com.simple.atm.dao;

import com.simple.atm.entity.CustomerInfo;

import java.util.HashMap;
import java.util.Map;

public class LoginDao {
    private final Map<String, CustomerInfo> store = new HashMap<String, CustomerInfo>();

    public CustomerInfo findByCard(String card) {
        return store.get(card);
    }

    public void save(CustomerInfo info) {
        store.put(info.getCardNumber(), info);
    }
}
