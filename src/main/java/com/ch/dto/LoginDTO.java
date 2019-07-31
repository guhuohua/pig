package com.ch.dto;

import io.swagger.models.auth.In;

public class LoginDTO {
    private String member;

    private Integer useIntegral;

    private String superiorInvitationCode;

    private String tel;

    private String invitationCode;

    private Integer signStatus;

    private Integer discount;

    private Integer nextMemberIntegral;

    private  String nextMember;

    private Integer integral;



    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getNextMemberIntegral() {
        return nextMemberIntegral;
    }

    public void setNextMemberIntegral(Integer nextMemberIntegral) {
        this.nextMemberIntegral = nextMemberIntegral;
    }

    public String getNextMember() {
        return nextMember;
    }

    public void setNextMember(String nextMember) {
        this.nextMember = nextMember;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

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

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
