package org.lucius.controller;

import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.lucius.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lucius
 * @Date: 2022-04-04 10:27
 */
@RestController
public class MqProducerController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @RequestMapping("mq/{info}")
    public String infoMQ(@PathVariable String info){

        //发送消息

        User user = new User(info,12);
//        rocketMQTemplate.send(MessageBuilder.withPayload(info).build());

//        普通发送
        rocketMQTemplate.convertAndSend("TopicTest",user);//convert 转换为底层数组
//        同步消息
        SendResult topicTest = rocketMQTemplate.syncSend("TopicTest", user);
//        异步消息
        rocketMQTemplate.asyncSend("TopicTest", user, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("成功");
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println(
                        "失败"
                );
            }
        });
//         单向消息
        rocketMQTemplate.sendOneWay("TopicTest",user);
//        延时消息
        rocketMQTemplate.syncSend("TopicTest",MessageBuilder.withPayload(user).build(),100);

        List<Message> list = new ArrayList<>();
        list.add(MessageBuilder.withPayload(user).build());
        rocketMQTemplate.syncSend("TopicTest",list,111);


        return user+"已发送至队列";
    }
}
