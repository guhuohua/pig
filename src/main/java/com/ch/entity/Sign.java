package com.ch.entity;

import java.util.Date;

public class Sign {
    private Integer id;

    private Date signDate;

    private String signSatus;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public String getSignSatus() {
        return signSatus;
    }

    public void setSignSatus(String signSatus) {
        this.signSatus = signSatus == null ? null : signSatus.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}