package com.ch.enums;

public enum MemberEnum {

    BRONZE("1", "青铜会员"),
    SILVER("2", "白银会员"),
    GOLD("3", "黄金会员"),
    PLATINUM("4", "白金会员"),
    MASONRY("5", "砖石会员");
    public final String code;

    public final String message;

    MemberEnum(String code, String message) {
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
        return this.code + "-" + this.message;
    }
}
