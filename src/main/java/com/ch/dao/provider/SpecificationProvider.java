package com.ch.dao.provider;

import com.ch.base.BeanUtils;

import java.util.Map;

public class SpecificationProvider {
    public String getList(Map<String, Object> map) {
        //获取参数列表
        String name = (String) map.get("name");
        Integer shopId = (Integer) map.get("shopId");
        StringBuffer sb = new StringBuffer("select id, title, sort from specification where 1=1 ");
        if (BeanUtils.isNotEmpty(name)) {
            sb.append(" and title  like '%").append(name).append("%'");
        }
        sb.append(" and shop_id = ").append(shopId);
        return sb.toString();
    }
}
