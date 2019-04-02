package com.ch.dao.provider;

import java.util.Map;

public class BtSysUserProvider {

    public String getUserList(Map<String, Object> map) {
        //获取参数列表
        String userName = (String) map.get("userName");
        String roleId = (String) map.get("roleId");
        StringBuffer sb = new StringBuffer("select bsu.username, bsu.phone, bsr.role_desc, bsu.account, bsu.status, DATE_FORMAT(bsu.update_time,'%Y-%m-%d %H:%i:%s') as update_time, bsu.user_id, bsur.role_id from bt_sys_user bsu left join " +
                "  bt_sys_user_role bsur on bsu.user_id = bsur.user_id left join " +
                "  bt_sys_role bsr on bsur.role_id = bsr.role_id where 1=1 ");
        if (userName != null) {
            sb.append(" and bsu.username like '%").append(userName).append("%'");
        }
        if (roleId != null) {
            sb.append(" and bsr.role_id = '").append(roleId).append("'");
        }
        return sb.toString();
    }
}
