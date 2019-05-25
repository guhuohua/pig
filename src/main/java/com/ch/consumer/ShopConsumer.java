package com.ch.consumer;

import com.ch.service.SysOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@EnableJms
@Component
public class ShopConsumer {

    @Autowired
    SysOrderService sysOrderService;

    @JmsListener(destination = "cancelOrder")
    public void customer(String msg) {
        log.info("收到超时未支付取消订单MQ，orderId:[{}]", msg);
        try {
            sysOrderService.cancelOrder(msg);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("处理超时未支付取消订单MQ失败, orderId[{}]", msg);
        }
    }

    @JmsListener(destination = "cancelOrder")
    public void delivery(String msg) {
        log.info("收到7天自动收货MQ，orderId:[{}]", msg);
        try {
            sysOrderService.deliver(msg);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("处理7天自动收货MQ失败, orderId:[{}]", msg);
        }
    }
}
