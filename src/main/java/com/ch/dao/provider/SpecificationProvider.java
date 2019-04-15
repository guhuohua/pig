package com.ch.dao.provider;

import com.ch.base.BeanUtils;

import java.util.Map;

public class SpecificationProvider {
    public String getList(Map<String, Object> map) {
        //获取参数列表
        String name = (String) map.get("title");
        Integer shopId = (Integer) map.get("shopId");
        StringBuffer sb = new StringBuffer("select id, title, sort from specification");
        if (BeanUtils.isNotEmpty(name)) {
            sb.append(" and name  like '%").append(name).append("%'");
        }
        sb.append(" and g.shop_id = ").append(shopId);
        return sb.toString();
    }
}
