package com.ch.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@EnableJms
@Component
public class ShopConsumer {

    @JmsListener(destination = "cancelOrder")
    public void customer(String msg) {
        System.out.println("接收到的消息:");
        System.out.println(msg);
    }
}
