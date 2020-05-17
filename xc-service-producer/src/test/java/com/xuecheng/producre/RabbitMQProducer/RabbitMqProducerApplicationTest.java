package com.xuecheng.producre.RabbitMQProducer;

import com.xuecheng.framework.baseConstant.RabbitMQConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMqProducerApplicationTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void testProducer() {
        rabbitTemplate.convertAndSend(RabbitMQConstant.TopicConstant.TOPIC_EXCHANGE_NAME, "inform.email", RabbitMQConstant.TopicConstant.MESSAGE);
    }
}
