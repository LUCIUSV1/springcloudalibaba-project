package org.lucius.one2many;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

public class Producer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
         DefaultMQProducer defaultMQProducer = new DefaultMQProducer("group1");
         defaultMQProducer.setNamesrvAddr("localhost:9876");
         defaultMQProducer.start();

        for (int i = 0; i < 10; i++) {
            String msg = "Hello 大帝"+i;
            Message message = new Message("TopicTest","tag1",msg.getBytes());
            SendResult send = defaultMQProducer.send(message);
            System.out.println(send);
        }

        defaultMQProducer.shutdown();
    }
}
