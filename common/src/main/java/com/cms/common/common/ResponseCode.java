package com.cms.common.common;

/**
 * @author Creams
 */

public enum ResponseCode {
    SUCCESS(0, "success"),
    FAILURE(1, "failure");

    private int code;
    private String info;


    ResponseCode(int code, String info) {
        this.code = code;
        this.info = info;
    }


    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
