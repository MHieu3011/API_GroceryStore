package com.vcc.apigrocerystore.exception;

public class CommonException extends Exception {
    private int code;

    public CommonException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
