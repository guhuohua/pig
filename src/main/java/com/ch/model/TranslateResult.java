package com.ch.model;

import java.util.List;

public class TranslateResult {

    private String from;
    private String to;
    private List<Result> trans_result;
    public void setFrom(String from) {
        this.from = from;
    }
    public String getFrom() {
        return from;
    }

    public void setTo(String to) {
        this.to = to;
    }
    public String getTo() {
        return to;
    }

    public void setTrans_result(List<Result> trans_result) {
        this.trans_result = trans_result;
    }
    public List<Result> getTrans_result() {
        return trans_result;
    }
}
