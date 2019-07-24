package com.ch.dto;

public class LoginDTO {
    private String member;

    private Integer useIntegral;

    private String superiorInvitationCode;

    private String invitationCode;

    private Integer signStatus;

    private String discount;

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public Integer getUseIntegral() {
        return useIntegral;
    }

    public void setUseIntegral(Integer useIntegral) {
        this.useIntegral = useIntegral;
    }

    public String getSuperiorInvitationCode() {
        return superiorInvitationCode;
    }

    public void setSuperiorInvitationCode(String superiorInvitationCode) {
        this.superiorInvitationCode = superiorInvitationCode;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public Integer getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(Integer signStatus) {
        this.signStatus = signStatus;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
