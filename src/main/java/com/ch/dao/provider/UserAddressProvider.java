package com.ch.dao.provider;

import com.ch.base.BeanUtils;

import java.util.Map;

public class UserAddressProvider {

    public String getList(Map<String, Object> map) {
        //获取参数列表
        String name = (String) map.get("name");
        String tel = (String) map.get("tel");
        Integer shopId = (Integer) map.get("shopId");
        StringBuilder sb  = new StringBuilder("select id, name, tel, concat(province,  '-', city,'-',area,' ',address) as address from user_address where 1 = 1");
        if (BeanUtils.isNotEmpty(name)) {
            sb.append(" and name  like '%").append(name).append("%'");
        }
        if (BeanUtils.isNotEmpty(tel)) {
            sb.append(" and tel = ").append(tel);
        }
        sb.append(" and shop_id = ").append(shopId);
        return sb.toString();
    }
}
