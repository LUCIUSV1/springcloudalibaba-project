package org.lucius.type;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

public class Producer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
         DefaultMQProducer defaultMQProducer = new DefaultMQProducer("group1");
         defaultMQProducer.setNamesrvAddr("localhost:9876");
         defaultMQProducer.start();

//        for (int i = 0; i < 10; i++) {
//            String msg = "Hello 大帝"+i;
//            Message message = new Message("TopicTest","tag1",msg.getBytes());
//            //同步消息
//            SendResult send = defaultMQProducer.send(message);
//            System.out.println(send);
//        }


        for (int i = 0; i < 10; i++) {
            String msg = "狗鸡哥"+i;
            Message message = new Message("TopicTest","tag1",msg.getBytes());
//            //异步消息
//            defaultMQProducer.send(message, new SendCallback() {
//                @Override
//                public void onSuccess(SendResult sendResult) {
//                    System.out.println(sendResult);
//                }
//
//                @Override
//                public void onException(Throwable throwable) {
//                    System.out.println(throwable.getMessage());
//                }
//            });
//            System.out.println("异步发送完成");

//          //单向消息
//            defaultMQProducer.sendOneway(message);

            //延迟消息
            message.setDelayTimeLevel(3);
            defaultMQProducer.send(message);


        }
//        defaultMQProducer.shutdown();
    }
}
