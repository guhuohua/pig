package com.ch.dto;

public class AddOrderDTO {
    private String orderId;
    private Integer useIntegral;
    private Integer money;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getUseIntegral() {
        return useIntegral;
    }

    public void setUseIntegral(Integer useIntegral) {
        this.useIntegral = useIntegral;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
