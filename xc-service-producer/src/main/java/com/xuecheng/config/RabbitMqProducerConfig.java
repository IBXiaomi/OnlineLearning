package com.xuecheng.config;


import com.xuecheng.framework.baseConstant.RabbitMQConstant;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMqProducer绑定队列等
 *
 * @author 吧嘻小米
 * @date 2020/05/16
 */
@Configuration
public class RabbitMqProducerConfig {
    /**
     * 声明交换机
     *
     * @return 交换机
     */
    @Bean(RabbitMQConstant.TopicConstant.TOPIC_EXCHANGE_NAME)
    public Exchange getExchange() {
        return ExchangeBuilder.topicExchange(RabbitMQConstant.TopicConstant.TOPIC_EXCHANGE_NAME).durable(true).build();
    }

    /**
     * 声明EMAIL队列
     *
     * @return 队列
     */
    @Bean(RabbitMQConstant.TopicConstant.TOPIC_QUEUE_EMAIL)
    public Queue getEmailQueue() {
        return new Queue(RabbitMQConstant.TopicConstant.TOPIC_QUEUE_EMAIL);
    }

    /**
     * 声明SMS队列
     *
     * @return 队列
     */
    @Bean(RabbitMQConstant.TopicConstant.TOPIC_QUEUE_SMS)
    public Queue getSmsQueue() {
        return new Queue(RabbitMQConstant.TopicConstant.TOPIC_QUEUE_SMS);
    }

    /**
     * 绑定EMAIL队列和交换机
     *
     * @return Binding
     */
    @Bean
    public Binding bindingEmailExchangeAndQueue(@Qualifier(RabbitMQConstant.TopicConstant.TOPIC_QUEUE_EMAIL) Queue queue,
                                                @Qualifier(RabbitMQConstant.TopicConstant.TOPIC_EXCHANGE_NAME) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("inform.#.email.#").noargs();
    }


    /**
     * 绑定SMs队列和交换机
     *
     * @return Binding
     */
    @Bean
    public Binding bindingSmsExchangeAndQueue(@Qualifier(RabbitMQConstant.TopicConstant.TOPIC_QUEUE_SMS) Queue queue,
                                              @Qualifier(RabbitMQConstant.TopicConstant.TOPIC_EXCHANGE_NAME) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("inform.#.sms.#").noargs();
    }
}
