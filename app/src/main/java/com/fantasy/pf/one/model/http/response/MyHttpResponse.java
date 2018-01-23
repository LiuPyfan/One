package com.fantasy.pf.one.model.http.response;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/22.
 * let none that wait on thee be ashamed
 */

public class MyHttpResponse<T> {
    private int res;
    private T data;

    public MyHttpResponse(int res, T data) {
        this.res = res;
        this.data = data;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
