package com.rabbitmq;

import org.springframework.amqp.core.Message;
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
public class FanoutRabbitMQProducer implements RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate  rabbitTemplate;

    @PostConstruct
    public void init() {
//        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        System.out.println("消息唯一标识：" + correlationData);
//        System.out.println("确认结果：" + ack);
//        System.out.println("失败原因：" + cause);
//    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("消息主体 message : "+message);
        System.out.println("消息主体 message : "+replyCode);
        System.out.println("描述："+replyText);
        System.out.println("消息使用的交换器 exchange : "+exchange);
        System.out.println("消息使用的路由键 routing : "+routingKey);
    }

    @PostConstruct
    public void setup() {
        // 消息发送完毕后，则回调此方法 ack代表发送是否成功
        rabbitTemplate.setConfirmCallback((correlationData,ack,cause)-> {
                // ack为true，代表MQ已经准确收到消息
//                if (!ack) {
//                    return;
//                }
                System.out.println("消息唯一标识：" + correlationData);
                System.out.println("确认结果：" + ack);
                System.out.println("失败原因：" + cause);
        });
    }


    private AtomicInteger index = new AtomicInteger();

    public void send() {
        String message = "hello lby " + index.decrementAndGet();
        System.out.println("produce\t" + message);
        // 指定 消息交换机,路由关键字可以随意指定，但不能为空
        this.rabbitTemplate.convertAndSend(FanoutRabbitConfiguration.FANOUT_EXCHANGE_LBY, "", message,new CorrelationData(String.valueOf(index.get())));
//        this.rabbitTemplate.convertAndSend(FanoutRabbitConfiguration.FANOUT_QUEUE_LBY, message);
    }

}
