package com.ch.model;

public class WeiXinParam {
    private String orderId;
    private int integralStatus;
    private double money;
    private int useIntegral;

    private Integer redPacketId;

    public Integer getRedPacketId() {
        return redPacketId;
    }

    public void setRedPacketId(Integer redPacketId) {
        this.redPacketId = redPacketId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getIntegralStatus() {
        return integralStatus;
    }

    public void setIntegralStatus(int integralStatus) {
        this.integralStatus = integralStatus;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getUseIntegral() {
        return useIntegral;
    }

    public void setUseIntegral(int useIntegral) {
        this.useIntegral = useIntegral;
    }
}
