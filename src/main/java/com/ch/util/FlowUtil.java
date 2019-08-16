package com.ch.util;

import com.ch.dao.UserAccountFlowMapper;
import com.ch.entity.UserAccountFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class FlowUtil {
    @Autowired
  UserAccountFlowMapper userAccountFlowMapper;

    public  void addFlowTel(long price, String flowReason, String type, Integer status,Integer userId) {
        UserAccountFlow userAccountFlow = new UserAccountFlow();
        userAccountFlow.setCreateDate(new Date());
        userAccountFlow.setUserId(userId);
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
                    userAccountFlow.setFlowReason("绑定下级获得积分");
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
            if ("INTEGRAL".equals(flowReason)) {
                userAccountFlow.setType("INTEGRAL");
                userAccountFlow.setFlowReason("积分购物");
            }
            if ("INTEGRAL_MONEY".equals(flowReason)) {
                userAccountFlow.setType("INTEGRAL");
                userAccountFlow.setFlowReason("购买商品消耗积分");
            }

        }

        userAccountFlowMapper.insert(userAccountFlow);
    }


}
