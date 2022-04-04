package org.lucius.sort;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class Consumer {

    public static void main(String[] args)  throws Exception{
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("group1");
        defaultMQPushConsumer.setNamesrvAddr("localhost:9876");



        defaultMQPushConsumer.subscribe("zbc","*");
//注册监听器
        defaultMQPushConsumer.registerMessageListener(new MessageListenerOrderly() {

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                for (MessageExt m:list){
                    try {
                        System.out.println(new String(m.getBody(),"utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        defaultMQPushConsumer.start();

    }
}
