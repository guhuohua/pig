package com.ch.model;

public class TrafficStatisticsDTO {

    /**
     * 总访问量
     */
    private Long count;

    /**
     * 昨日访问量
     */
    private Long yesterday;

    /**
     * 月访问量
     */
    private Long month;

    /**
     * 当天访问量
     */
    private Long same;


    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getYesterday() {
        return yesterday;
    }

    public void setYesterday(Long yesterday) {
        this.yesterday = yesterday;
    }

    public Long getMonth() {
        return month;
    }

    public void setMonth(Long month) {
        this.month = month;
    }

    public Long getSame() {
        return same;
    }

    public void setSame(Long same) {
        this.same = same;
    }
}
