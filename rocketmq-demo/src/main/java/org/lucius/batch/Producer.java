package org.lucius.batch;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Producer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
     DefaultMQProducer defaultMQProducer = new DefaultMQProducer("group1");
     defaultMQProducer.setNamesrvAddr("localhost:9876");
     defaultMQProducer.start();

     //批量发送
     String msg = "狗鸡哥";
     Message message = new Message("TopicTest","tag1",msg.getBytes());

        List<Message> messages = new ArrayList<>();
        messages.add(message);
        messages.add(message);
        messages.add(message);

        SendResult send = defaultMQProducer.send(messages);
        System.out.println(send);

//        defaultMQProducer.shutdown();
    }
}
