package com.cms.common.common;


/**
 * @author Creams
 */

public enum ResponseCode {
    SUCCESS(0, "Success"),
    FAILURE(1, "Failure"),
    NULL(2, "Null Info"),
    NO_PERMISSION(3, "No permission"),
    ACCESS_DENIED(20003, "Access denied"),
    NO_LOGIN(20004, "No login"),
    LOGIN_FAILURE(20005, "Login Failure"),
    LOGIN_EXPIRE(20005, "Login expire"),

    ;


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
