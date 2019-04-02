package com.ch.base;

import java.util.List;

public class AddressJsonData {
    private String ret;
    private String ip;
    private List<String> data;
    public void setRet(String ret) {
        this.ret = ret;
    }
    public String getRet() {
        return ret;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getIp() {
        return ip;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
    public List<String> getData() {
        return data;
    }
}
