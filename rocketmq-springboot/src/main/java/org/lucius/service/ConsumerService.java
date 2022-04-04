package org.lucius.service;

import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.lucius.entity.User;
import org.springframework.stereotype.Service;

/**
 * @Author: Lucius
 * @Date: 2022-04-04 10:40
 */
@Service
//@RocketMQMessageListener(topic = "TopicTest",selectorExpression = "*",consumerGroup = "group1")
@RocketMQMessageListener(topic = "TopicTest",messageModel = MessageModel.CLUSTERING,selectorType = SelectorType.SQL92,selectorExpression = "age>1",consumerGroup = "group1")

public class ConsumerService implements RocketMQListener<User> {


    @Override
    public void onMessage(User user) {
        System.out.println("接收到:"+user);
    }
}
