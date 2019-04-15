/**
 * Author: 常富文
 * Date:   2019/4/12 16:18
 * Description: 商品規格
 */


package com.ch.model;

import java.util.List;

public class GoodsCategory {

    private String attrName;

    private List<String> attrValue;

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public List<String> getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(List<String> attrValue) {
        this.attrValue = attrValue;
    }
}
