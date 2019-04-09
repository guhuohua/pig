/**
 * Author: 常富文
 * Date:   2019/4/3 16:52
 * Description: 店铺参数
 */


package com.ch.dto;

import com.ch.base.PageQuery;

public class ShopParam extends PageQuery {
    private String title;
    private String tel;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
