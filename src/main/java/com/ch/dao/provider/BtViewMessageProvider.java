package com.ch.dao.provider;

import java.util.Map;

public class BtViewMessageProvider {

    public String getMessageList(Map<String, Object> map) {
        //获取参数列表
        String name = (String) map.get("name");
        String phone = (String) map.get("phone");
        StringBuffer sb = new StringBuffer("select * from bt_view_message where 1=1");
        if (name != null && name != "") {
            sb.append(" and name like '%").append(name).append("%'");
        }
        if (phone != null && phone !="") {
            sb.append(" and tel = ").append(phone);
        }
        sb.append(" order by message_time desc");
        return sb.toString();
    }
}
