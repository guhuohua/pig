package com.ch.entity;

public class Express {
    private Integer id;

    private String expressAbbreviation;

    private String expressName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpressAbbreviation() {
        return expressAbbreviation;
    }

    public void setExpressAbbreviation(String expressAbbreviation) {
        this.expressAbbreviation = expressAbbreviation == null ? null : expressAbbreviation.trim();
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName == null ? null : expressName.trim();
    }
}