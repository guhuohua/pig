/**
 * Author: 常富文
 * Date:   2019/4/3 16:52
 * Description: 店铺参数
 */


package com.ch.dto;

import com.ch.base.PageQuery;

public class ShopParam extends PageQuery {
    private String title;
    private Integer id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
