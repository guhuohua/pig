package com.ch.dao.provider;

import com.ch.base.BeanUtils;

import java.util.Map;

public class SpikeGoodsProvider {


    public String getList(Map<String, Object> map) {
        String sn = (String) map.get("sn");
        StringBuffer sb = new StringBuffer("select sk.id as spikeId,  gs.id, gs.sn, gs.title, unix_timestamp(sk.begin_date) as beginDate, unix_timestamp(sk.end_date) as endDate," +
                "    gs.present_price, sk.spike_price, sk.spike_num, gs.inventory, sk.max_num from spike_goods sk left join goods gs on sk.goods_id = gs.id where 1=1");
        if (BeanUtils.isNotEmpty(sn)) {
            sb.append(" and sn = ").append(sn);
        }
        return sb.toString();
    }
}
