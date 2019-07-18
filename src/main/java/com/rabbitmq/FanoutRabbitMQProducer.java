package com.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by LiBingyi on 2019/7/18 14:02
 */
@Component
public class FanoutRabbitMQProducer {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    private AtomicInteger index = new AtomicInteger();

    public void send() {
        String message = "hello lby " + index.decrementAndGet();
        System.out.println("produce\t" + message);
        // 指定 消息交换机,路由关键字可以随意指定，但不能为空
        this.rabbitTemplate.convertAndSend(FanoutRabbitConfiguration.FANOUT_EXCHANGE_LBY, "", message);
//        this.rabbitTemplate.convertAndSend(FanoutRabbitConfiguration.FANOUT_QUEUE_LBY, message);
    }

}
