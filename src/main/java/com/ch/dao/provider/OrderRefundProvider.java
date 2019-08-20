package com.ch.dao.provider;

import com.ch.base.BeanUtils;

import java.util.Map;

public class OrderRefundProvider {

    public String getList(Map<String, Object> map) {
        //获取参数列表
        String name = (String) map.get("name");
        String refundId = (String) map.get("refundId");
        Integer refundStatus = (Integer) map.get("refundStatus");
        Integer shopId = (Integer) map.get("shopId");
        StringBuilder sb = new StringBuilder("select orr.id as refund_id, go.id as order_id, ua.name ,orr.price as refund_price,  " +
                "orr.real_price, go.pay_date, go.track_number as logistics_number , orr.create_date as refund_date, orr.refund_status"+
                "   from order_refund orr" +
                "   left join goods_order go on orr.order_id = go.id" +
                "   left join user_address ua on go.delivery_id = ua.id where 1=1");
        if (BeanUtils.isNotEmpty(name)) {
            sb.append(" and ua.name  like '%").append(name).append("%'");
        }
        if (BeanUtils.isNotEmpty(refundId)) {
            sb.append(" and orr.id = '").append(refundId).append("'");
        }
        if (BeanUtils.isNotEmpty(refundStatus)) {
            sb.append(" and orr.refund_status = ").append(refundStatus);
        }
        sb.append(" and orr.shop_id =").append(shopId);
        sb.append(" order by orr.create_date desc");
        return sb.toString();
    }
}
