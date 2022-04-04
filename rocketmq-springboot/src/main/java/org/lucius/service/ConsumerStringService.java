package org.lucius.service;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @Author: Lucius
 * @Date: 2022-04-04 10:44
 */
@Service
@RocketMQMessageListener(topic = "TopicTest",selectorExpression = "*",consumerGroup = "group1")
public class ConsumerStringService implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        System.out.println("乌拉"+s);
    }
}
