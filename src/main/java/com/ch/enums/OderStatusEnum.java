package com.ch.enums;

public enum OderStatusEnum {

    UNPAID("1", "未支付"),
    PAID("2", "已支付"),
    UNSHIPPED("3", "待发货"),
    SHIPPED("4", "已发货"),
    UNRECEIVED("5", "待收货"),
    RECEIVED("6", "已收货"),
    COMPLETE("7", "订单完成"),
    CANCEL("8", "订单取消"),
    UNEVALUATED("9", "待评价"),
    REFUNDNO("10","已退款");



    public final String code;

    public final String message;

    OderStatusEnum(String code, String message ) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }
    public String getMessage() {
        return this.message;
    }

    public String getExceptionMessage() {
        return this.code +"-"+this.message;
    }
}
