package com.fantasy.pf.one.model.http.exception;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/22.
 * let none that wait on thee be ashamed
 */

public class ApiException extends Exception {
    private int code;

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, int code) {
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
