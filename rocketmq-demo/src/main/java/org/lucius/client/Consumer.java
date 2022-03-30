package org.lucius.client;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class Consumer {

    public static void main(String[] args)  throws Exception{
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("group1");
        defaultMQPushConsumer.setNamesrvAddr("localhost:9876");



        defaultMQPushConsumer.subscribe("RMQ_SYS_TRANS_HALF_TOPIC","*");
//注册监听器
        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

                for (MessageExt m:list){
                    try {
                        System.out.println(new String(m.getBody(),"utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        defaultMQPushConsumer.start();

    }
}
