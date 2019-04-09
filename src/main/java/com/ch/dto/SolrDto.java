/**
 * Author: 常富文
 * Date:   2019/4/9 11:59
 * Description: 搜索条件
 */


package com.ch.dto;

public class SolrDto {
    private Integer start;

    private  Integer rows;

    private  String condition;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
