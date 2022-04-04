package org.lucius.sort;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Producer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
         DefaultMQProducer defaultMQProducer = new DefaultMQProducer("group1");
         defaultMQProducer.setNamesrvAddr("localhost:9876");
         defaultMQProducer.start();

        List<OrderStep> orderSteps = new ArrayList<>();
        OrderStep orderStep1 = new OrderStep(1L,"创建");
        orderSteps.add(orderStep1);
        OrderStep orderStep2 = new OrderStep(2L,"创建");
        orderSteps.add(orderStep2);
        OrderStep orderStep3 = new OrderStep(1L,"付款");
        orderSteps.add(orderStep3);
        OrderStep orderStep4 = new OrderStep(3L,"创建");
        orderSteps.add(orderStep4);
        OrderStep orderStep5 = new OrderStep(1L,"支付");
        orderSteps.add(orderStep5);
        OrderStep orderStep6 = new OrderStep(1L,"完成");
        orderSteps.add(orderStep6);
        OrderStep orderStep7 = new OrderStep(2L,"付款");
        orderSteps.add(orderStep7);
        OrderStep orderStep8 = new OrderStep(3L,"付款");
        orderSteps.add(orderStep8);

//        for(OrderStep orderStep:orderSteps){
//            Message message = new Message("zbc", "tag1", orderStep.toString().getBytes(StandardCharsets.UTF_8));
//            SendResult send = defaultMQProducer.send(message);
//            System.out.println(send);
//        }

//        消息顺序发送
        for (OrderStep orderStep : orderSteps) {
            Message message = new Message("zbc", "tag1", orderStep.toString().getBytes(StandardCharsets.UTF_8));
            SendResult send = defaultMQProducer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {

                    //orderId 对应一个确定的队列

                    //队列数
                    int size = list.size();

                    //取模
                    int id =(int) orderStep.getId();
                    int m = id%size;
                    //取出确定队列
                    System.out.println(list.get(m));
                    return list.get(m);
                }
            }, null);
            System.out.println(send);
        }
    }
}
