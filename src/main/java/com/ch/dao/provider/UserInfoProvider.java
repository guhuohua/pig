package com.ch.dao.provider;

import com.ch.base.BeanUtils;

import java.util.Map;

public class UserInfoProvider {

    public String getList(Map<String, Object> map) {
        //获取参数列表
        String name = (String) map.get("name");
        String tel = (String) map.get("tel");
        Integer shopId = (Integer) map.get("shopId");
        StringBuilder sb = new StringBuilder("select ui.id, ui.user_head,ui.nickname,ui.tel, ui.gender, if(o.order_status =7, (select sum(order_price) from `order` where shop_id = 1),0 ) as orderPrice," +
                " if(o.order_status =7, (select count(*) from `order` where shop_id = 1 ),0 ) as orderCount" +
                " from user_info ui left join `order` o on o.user_id = ui.id and o.shop_id = ui.shop_id where 1 = 1");
        sb.append(" and ui.shop_id = ").append(shopId);
        if (BeanUtils.isNotEmpty(name)) {
            sb.append(" and ui.nikename  like '%").append(name).append("%'");
        }
        if (BeanUtils.isNotEmpty(tel)) {
            sb.append(" and ui.tel = ").append(tel);
        }
        return sb.toString();
    }
}
