package com.ch.handler;

import com.ch.config.ActiveMQProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Slf4j
@Component
public class ActiveMQHandler {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    ActiveMQProperties activeMQProperties;

    public void cancelOrder(String queueName, String orderId) {
        log.info("====>>> 延时任务(15分钟取消订单):" + queueName + ",data:" + orderId);
        //获取连接工厂
        ConnectionFactory connectionFactory = jmsMessagingTemplate.getConnectionFactory();
        try {
            //获取连接
            assert connectionFactory != null;
            Connection connection = connectionFactory.createConnection();
            connection.start();
            //获取session, true开启事务，false关闭事务
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            Destination destination = session.createQueue(queueName);
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            TextMessage message = session.createTextMessage(orderId);
            //设置延迟时间 //AMQ_SCHEDULED_DELAY
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, activeMQProperties.getCancel());
            //发送
            producer.send(message);
            session.commit();
            producer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delivery(String queueName, String orderId) {
        log.info("====>>> 延时任务（7天自动收货）:" + queueName + ",data:" + orderId);
        //获取连接工厂
        ConnectionFactory connectionFactory = jmsMessagingTemplate.getConnectionFactory();
        try {
            //获取连接
            assert connectionFactory != null;
            Connection connection = connectionFactory.createConnection();
            connection.start();
            //获取session, true开启事务，false关闭事务
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            Destination destination = session.createQueue(queueName);
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            TextMessage message = session.createTextMessage(orderId);
            //设置延迟时间 //AMQ_SCHEDULED_DELAY
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, activeMQProperties.getDelivery());
            //发送
            producer.send(message);
            session.commit();
            producer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
