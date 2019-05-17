package com.ch.dao.provider;

import com.ch.base.BeanUtils;

import java.util.Map;

public class GoodsEvaluationProvider {

    public String getList(Map<String, Object> map) {
        //获取参数列表
        String name = (String) map.get("name");
        Integer status = (Integer) map.get("status");
        Integer shopId = (Integer) map.get("shopId");
        Integer score = (Integer) map.get("score");
        StringBuilder sb = new StringBuilder("select id, order_id, score, name, create_time, status, details as content from goods_evaluation where 1 = 1");
        if (BeanUtils.isNotEmpty(name)) {
            sb.append(" and name  like '%").append(name).append("%'");
        }
        if (BeanUtils.isNotEmpty(score)) {
            if (1 == score) {
                sb.append(" and score in (1,2)");
            }
            if (3 == score) {
                sb.append(" and score in (3,4)");
            }
            if (5 == score) {
                sb.append(" and score = 5");
            }
        }
        if (null != status) {
            sb.append(" and status = ").append(status);
        }
        sb.append(" and shop_id =").append(shopId);
        return sb.toString();
    }
}
