package com.ch.util;

import com.ch.dao.UserAccountFlowMapper;
import com.ch.entity.UserAccountFlow;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class FlowUtil {
    @Autowired
   static UserAccountFlowMapper userAccountFlowMapper;

    public static void addFlowTel(long price, String flowReason, String type, Integer status) {
        UserAccountFlow userAccountFlow = new UserAccountFlow();
        userAccountFlow.setCreateDate(new Date());
        userAccountFlow.setPrice(price);
        if (0 == status) {
            if ("INTEGRAL".equals(type)) {
                userAccountFlow.setType("INTEGRAL");
                userAccountFlow.setStatus(0);
                if ("tel".equals(flowReason)) {
                    userAccountFlow.setFlowReason("绑定手机号获得积分");
                }
                if ("sign".equals(flowReason)) {
                    userAccountFlow.setFlowReason("签到获得积分");
                }
                if ("comment".equals(flowReason)) {
                    userAccountFlow.setFlowReason("评论获得积分");
                }
                if ("first".equals(flowReason)) {
                    userAccountFlow.setFlowReason("绑定上级获得积分");
                }

                if ("payment".equals(flowReason)) {
                    userAccountFlow.setFlowReason("购买商品级获得积分");
                }

                if ("super".equals(flowReason)) {
                    userAccountFlow.setFlowReason("下级分销商消费获得积分");
                }
            }

        }
        if (1 == status) {
            userAccountFlow.setStatus(1);
            if ("INTEGRAL".equals(type)) {
                userAccountFlow.setType("INTEGRAL");
                userAccountFlow.setFlowReason("积分购物");
            }
        }

        userAccountFlowMapper.insert(userAccountFlow);
    }


}
