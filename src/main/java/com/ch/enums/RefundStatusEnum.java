package com.ch.enums;

public enum RefundStatusEnum {

    REFUNDING("1", "售后中"),
    REFUNDTHROUGH("2", "售后通过"),
    REFUNDREFUSE("3", "售后拒绝");

    public final String code;

    public final String message;

    RefundStatusEnum(String code, String message ) {
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
