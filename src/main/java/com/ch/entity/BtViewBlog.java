package com.ch.entity;

public class BtViewBlog {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bt_view_blog.id
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bt_view_blog.access _number
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    private Long accessNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bt_view_blog.area
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    private String area;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bt_view_blog.ip
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    private String ip;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bt_view_blog.id
     *
     * @return the value of bt_view_blog.id
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bt_view_blog.id
     *
     * @param id the value for bt_view_blog.id
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bt_view_blog.access _number
     *
     * @return the value of bt_view_blog.access _number
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public Long getAccessNumber() {
        return accessNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bt_view_blog.access _number
     *
     * @param accessNumber the value for bt_view_blog.access _number
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public void setAccessNumber(Long accessNumber) {
        this.accessNumber = accessNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bt_view_blog.area
     *
     * @return the value of bt_view_blog.area
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public String getArea() {
        return area;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bt_view_blog.area
     *
     * @param area the value for bt_view_blog.area
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bt_view_blog.ip
     *
     * @return the value of bt_view_blog.ip
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bt_view_blog.ip
     *
     * @param ip the value for bt_view_blog.ip
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }
}