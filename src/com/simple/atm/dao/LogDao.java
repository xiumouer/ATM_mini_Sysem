package com.simple.atm.dao;

import com.simple.atm.entity.LogInfo;

import java.util.ArrayList;

public class LogDao {
    private final ArrayList<LogInfo> logs = new ArrayList<LogInfo>();

    public void add(LogInfo log) {
        logs.add(log);
    }

    public ArrayList<LogInfo> all() {
        return logs;
    }
}
