package com.simple.atm.dao;

import com.simple.atm.entity.ChargeRecorder;

import java.util.ArrayList;

public class ChargeDao {
    private final ArrayList<ChargeRecorder> records = new ArrayList<ChargeRecorder>();

    public void add(ChargeRecorder recorder) {
        records.add(recorder);
    }

    public ArrayList<ChargeRecorder> all() {
        return records;
    }
}
