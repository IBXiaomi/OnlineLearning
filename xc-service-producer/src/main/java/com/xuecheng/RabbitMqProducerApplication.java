package com.xuecheng;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot集成producer
 *
 * @author 吧嘻小米
 * @date 2020/05/16
 */
@SpringBootApplication
public class RabbitMqProducerApplication {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqProducerApplication.class, args);
    }
}
