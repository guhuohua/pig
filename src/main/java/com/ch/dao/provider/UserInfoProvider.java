package com.ch.dao.provider;

import com.ch.base.BeanUtils;

import java.util.Map;

public class UserInfoProvider {

    public String getList(Map<String, Object> map) {
        //获取参数列表
        String name = (String) map.get("name");
        String tel = (String) map.get("tel");
        Integer shopId = (Integer) map.get("shopId");
        StringBuilder sb = new StringBuilder("select ui.id, ui.user_head,ui.nickname,ui.tel, ui.gender from user_info ui where 1 = 1");
        sb.append(" and ui.shop_id = ").append(shopId);
        if (BeanUtils.isNotEmpty(name)) {
            sb.append(" and ui.nickname  like '%").append(name).append("%'");
        }
        if (BeanUtils.isNotEmpty(tel)) {
            sb.append(" and ui.tel = ").append(tel);
        }
        return sb.toString();
    }
}
