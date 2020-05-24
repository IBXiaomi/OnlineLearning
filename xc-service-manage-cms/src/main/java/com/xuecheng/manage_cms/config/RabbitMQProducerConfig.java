package com.xuecheng.manage_cms.config;

import com.xuecheng.framework.baseConstant.RabbitMQConstant;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * producer绑定队列等
 *
 * @author 吧嘻小米
 * @date 2020/05/24
 */
@Configuration
public class RabbitMQProducerConfig {


    /**
     * 声明页面交换机
     *
     * @return 交换机
     */
    @Bean(RabbitMQConstant.directConstant.CMS_PAGE_DIRECT_EXCHANGE)
    public Exchange getCmsPageExchange() {
        return ExchangeBuilder.directExchange(RabbitMQConstant.directConstant.CMS_PAGE_DIRECT_EXCHANGE).durable(true).build();
    }

}
