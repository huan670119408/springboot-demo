package com.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by LiBingyi on 2019/7/18 14:02
 */
@Component
public class FanoutRabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //    @PostConstruct
//    public void init() {
//        rabbitTemplate.setConfirmCallback(this);
//        rabbitTemplate.setReturnCallback(this);
//    }
//
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        System.out.println("消息唯一标识：" + correlationData);
//        System.out.println("确认结果：" + ack);
//        System.out.println("失败原因：" + cause);
//    }
//
//    @Override
//    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//        System.out.println("消息主体 message : " + message);
//        System.out.println("消息主体 message : " + replyCode);
//        System.out.println("描述：" + replyText);
//        System.out.println("消息使用的交换器 exchange : " + exchange);
//        System.out.println("消息使用的路由键 routing : " + routingKey);
//    }
//
    @PostConstruct
    public void init() {
//        // 消息发送完毕后，则回调此方法 ack代表发送是否成功
//        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
//            // ack为true，代表MQ已经准确收到消息
////                if (!ack) {
////                    return;
////                }
//            System.out.println("消息唯一标识：" + correlationData);
//            System.out.println("确认结果：" + ack);
//            System.out.println("失败原因：" + cause);
        // 消息发送失败返回到队列中, yml需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);
        // 消息返回, yml需要配置 publisher-returns: true
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            logger.debug("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
            System.out.println("1111111111111111111");
        });
        // 消息确认, yml需要配置 publisher-confirms: true
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                System.out.println("消息发送到exchange成功,id" + correlationData.getId());
            } else {
                logger.debug("消息发送到exchange失败,原因: {}", cause);
                System.out.println("消息发送到exchange失败,原因:" + cause);
            }
        });
    }


    private AtomicInteger index = new AtomicInteger();

    public void send() {
        String message = "hello lby " + index.decrementAndGet();
        System.out.println("发送消息\t" + message);
        // 指定 消息交换机,路由关键字可以随意指定，但不能为空
        this.rabbitTemplate.convertAndSend(FanoutRabbitConfiguration.FANOUT_EXCHANGE_LBY, "", message, new CorrelationData(String.valueOf(index.get())));
//        this.rabbitTemplate.convertAndSend(FanoutRabbitConfiguration.FANOUT_QUEUE_LBY, message);
    }

}
