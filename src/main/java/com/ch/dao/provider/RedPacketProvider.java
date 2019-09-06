package com.ch.dao.provider;

import com.ch.base.BeanUtils;

import java.util.Map;

public class RedPacketProvider {
    public String getList(Map<String, Object> map) {
        //获取参数列表
        String name = (String) map.get("name");
        Long useBeginDate = (Long) map.get("useBeginDate");
        Long useEndDate = (Long) map.get("useEndDate");
        Integer status = (Integer) map.get("status");
        String redPacketType = (String) map.get("redPacketType");
        StringBuilder sb = new StringBuilder("select id, name, face_value, concat(send_begin_date,'~' ,send_end_date) as expiryDate, red_packet_type, stock,status from red_packet where 1 = 1");
        if (BeanUtils.isNotEmpty(name)) {
            sb.append(" and name like '%").append(name).append("%'");
        }
        if (null != useBeginDate && null != useEndDate) {
            sb.append(" and send_end_date between ").append(useBeginDate).append(" and ").append(useEndDate);
        }
        if (null != status) {
            sb.append(" and status = ").append(status);
        }
        if (BeanUtils.isNotEmpty(redPacketType)) {
            sb.append(" and red_packet_type = ").append(redPacketType);
        }
        sb.append(" order by send_begin_date desc");
        return sb.toString();
    }

    public String getGrantList(Map<String, Object> map) {
        //获取参数列表
        String name = (String) map.get("name");
        Integer status = (Integer) map.get("status");
        StringBuilder sb = new StringBuilder("select urt.id, rp.face_value, rp.red_packet_type, urt.status, ui.nickname, unix_timestamp(urt.receive_date) as receiveDate, unix_timestamp(urt.modify_date) as useDate  from user_red_packet urt " +
                " left join red_packet rp on urt.red_packet_id = rp.id left join user_info ui on rp.user_id = ui.id where 1 = 1");
        if (BeanUtils.isNotEmpty(name)) {
            sb.append(" and rp.name like'%").append(name).append("%'");
        }
        if (null != status) {
            sb.append(" and urt.status = ").append(status);
        }
        sb.append(" order by urt.create_date desc");
        return sb.toString();
    }
}
