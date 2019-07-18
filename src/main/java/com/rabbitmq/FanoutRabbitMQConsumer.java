package com.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by LiBingyi on 2019/7/18 14:16
 */
@Component
public class FanoutRabbitMQConsumer {

    /**
     * 监听方法传入的参数需要和消息生产者的一致
     */
    @RabbitHandler
    @RabbitListener(queues = FanoutRabbitConfiguration.FANOUT_QUEUE_LBY)
    public void process(String msg) {
        System.out.println("Consumer\t"+msg);
    }


}
