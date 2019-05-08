package com.ch.dao.provider;

import com.ch.base.BeanUtils;

import java.util.Map;

public class SysGoodsAreaProvider {

    public String getList(Map<String, Object> map) {
        //获取参数列表
        String title = (String) map.get("title");
        String recommend = (String) map.get("recommend");
        Integer status = (Integer) map.get("status");
        Integer shopId = (Integer) map.get("shopId");
        StringBuffer sb = new StringBuffer("select ga.id, g.id as goodsId, g.sn, g.title, ga.goods_classification as recommend, ga.status, ga.id as goodsAreaId from goods g left join goods_area ga on g.id = ga.goods_id and g.shop_id = ga.shop_id where 1 = 1");
        if (BeanUtils.isNotEmpty(title)) {
            sb.append(" and g.title  like '%").append(title).append("%'");
        }
        if (BeanUtils.isNotEmpty(recommend)) {
            sb.append(" and ga.goods_classification = ").append("'").append(recommend).append("'");
        }
        if (BeanUtils.isNotEmpty(status)) {
            sb.append(" and ga.status = ").append(status);
        }
        sb.append(" and g.shop_id = ").append(shopId);return sb.toString();
    }
}
