package com.simple.atm.entity;

import java.io.Serializable;
import java.util.UUID;

public class CommonResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String message;
    private boolean status;

    public CommonResult() {
        this.id = UUID.randomUUID().toString();
    }

    public CommonResult(boolean status, String message) {
        this.id = UUID.randomUUID().toString();
        this.status = status;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
