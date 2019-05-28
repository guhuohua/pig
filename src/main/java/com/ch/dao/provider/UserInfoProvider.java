package com.ch.dao.provider;

import com.ch.base.BeanUtils;
import com.ch.dto.SysOrderParam;

import java.util.Date;
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

    public String orderList(Map<String, Object> map) {
        //获取参数列表
        SysOrderParam param = (SysOrderParam) map.get("param");
        Integer shopId = (Integer) map.get("shopId");
        StringBuffer sb = new StringBuffer("select id, (select name from user_address where id = delivery_id) as userName , order_status, order_price, create_date, track_number from goods_order" +
                " where 1=1");
        sb.append(" and shop_id = ").append(shopId);
        if (BeanUtils.isNotEmpty(param.getId())) {
            sb.append(" and id = ").append(param.getId());
        }
        if (BeanUtils.isNotEmpty(param.getUserName())) {
            sb.append(" and delivery_id in (select id from user_address where name like '%").append(param.getUserName()).append("%')");
        }
        if (BeanUtils.isNotEmpty(param.getBeginDate()) && BeanUtils.isNotEmpty(param.getEndDate())) {
            sb.append(" and create_date between ").append(new Date(param.getBeginDate())).append(" and ").append(new Date(param.getEndDate()));
        }
        if (BeanUtils.isNotEmpty(param.getOrderStatus())) {
            sb.append(" and order_status = ").append(param.getOrderStatus());
        }
        return sb.toString();
    }
}
