package com.cms.common.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Creams
 */
@Data
@AllArgsConstructor
public class ServerResponse<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    public static <T> ServerResponse<T> createSuccessResponse(T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), data);
    }

    public static <T> ServerResponse<T> createSuccessResponse() {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), null);
    }

    public static <T> ServerResponse<T> createFailureResponse(int code, String msg, T data) {
        return new ServerResponse<T>(code, msg, data);
    }

    public static <T> ServerResponse<T> createFailureResponse(int code, String msg) {
        return new ServerResponse<T>(ResponseCode.FAILURE.getCode(), ResponseCode.FAILURE.getInfo(), null);
    }

    public static <T> ServerResponse<T> createFailureResponse(String msg) {
        return new ServerResponse<T>(ResponseCode.FAILURE.getCode(), msg, null);
    }


}
