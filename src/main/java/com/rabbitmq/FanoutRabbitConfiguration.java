package com.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by LiBingyi on 2019/7/18 13:50
 */
@Configuration
public class FanoutRabbitConfiguration {

    /**
     * 任何发送到Fanout Exchange的消息都会被转发到与该Exchange绑定(Binding)的所有Queue上,不需要路由关键字匹配
     * <p>
     * 1.这种模式需要提前将Exchange与Queue进行绑定，一个Exchange可以绑定多个Queue，一个Queue可以同多个Exchange进行绑定
     * <p>
     * 2.这种模式不需要RouteKey
     * <p>
     * 3.如果接受到消息的Exchange没有与任何Queue绑定，则消息会被抛弃。
     */

    public static final String FANOUT_QUEUE_LBY = "fanout.queue.lby";

    public static final String FANOUT_EXCHANGE_LBY = "fanout.exchange.lby";

    @Bean("fanoutQueueLby")
    public Queue fanoutQueueLby() {
        return new Queue(FANOUT_QUEUE_LBY);
    }

    @Bean("fanoutExchangeLby")
    public FanoutExchange fanoutExchangeLby() {
        return new FanoutExchange(FANOUT_EXCHANGE_LBY);
    }

    @Bean
    public Binding bindingExchangeA(Queue fanoutQueueLby, FanoutExchange fanoutExchangeLby) {
        return BindingBuilder.bind(fanoutQueueLby).to(fanoutExchangeLby);
    }

}
