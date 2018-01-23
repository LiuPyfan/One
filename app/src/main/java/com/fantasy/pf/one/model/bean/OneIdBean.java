package com.fantasy.pf.one.model.bean;

import java.util.List;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/22.
 * let none that wait on thee be ashamed
 */

public class OneIdBean {
    private int res;
    private List<String> data;


    public OneIdBean() {
    }


    public OneIdBean(int res, List<String> data) {
        this.res = res;
        this.data = data;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public List<String> getData() {
        return data;
    }
    public void setData(List<String> data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "OneIdBean{" +
                "res=" + res +
                ", data=" + data +
                '}';
    }

}
