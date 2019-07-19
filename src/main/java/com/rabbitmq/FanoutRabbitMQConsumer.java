package com.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by LiBingyi on 2019/7/18 14:16
 */
@Component
public class FanoutRabbitMQConsumer {

    /**
     * 监听方法传入的参数需要和消息生产者的一致
     */
//    @RabbitHandler
//    @RabbitListener(queues = FanoutRabbitConfiguration.FANOUT_QUEUE_LBY)
//    public void process(String msg) {
//        System.out.println("Consumer\t" + msg);
//    }

    @RabbitHandler
    @RabbitListener(queues = FanoutRabbitConfiguration.FANOUT_QUEUE_LBY)
    public void processMessageACK(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        System.out.println(message);
        try {
            System.out.println("确认消息：" + message);
            channel.basicAck(tag, false);            // 确认消息
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
