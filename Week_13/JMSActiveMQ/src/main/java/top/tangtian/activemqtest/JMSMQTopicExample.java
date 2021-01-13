package top.tangtian.activemqtest;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.io.IOException;


/**
 * @author tangtian
 * @version 1.0
 * @className JMSMQExample
 * https://docs.aws.amazon.com/zh_cn/amazon-mq/latest/developer-guide/amazon-mq-working-java-example.html
 * @description
 * @date 2021/1/11 7:30 AM
 **/
public class JMSMQTopicExample {
    private static final Logger LOG = LoggerFactory.getLogger(JMSMQTopicExample.class);

    public static void main(String[] args) throws JMSException, InterruptedException {
        final ActiveMQConnectionFactory connectionFactory =
                ConnectionFactory.createActiveMQConnectionFactory();
        final PooledConnectionFactory pooledConnectionFactory =
                ConnectionFactory.createPooledConnectionFactory(connectionFactory);
        //会发现发布的10条消息并没有被消费者接收，
        // 因为在主题模式中： 只有提前进行订阅的消费者才能成功消费消息。
        // 而队列模式中消费者不需要提前订阅也可以消费消息。
        receiveTopicMessage(connectionFactory);
        int i = 0;
        while (++i < 100){
            sendTopicMessage(pooledConnectionFactory);
        }
        pooledConnectionFactory.stop();
    }


    private static void sendTopicMessage(PooledConnectionFactory pooledConnectionFactory) throws JMSException {
        // Establish a connection for the producer.
        final Connection producerConnection = pooledConnectionFactory
                .createConnection();
        producerConnection.start();

        // Create a session.
        final Session producerSession = producerConnection
                .createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create a topic named "MyTopic".
        final Destination producerDestination = producerSession
                .createTopic("MyTopic");

        // Create a producer from the session to the topic.
        final MessageProducer producer = producerSession
                .createProducer(producerDestination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        // Create a message.
        final String text = "Hello World";
        final TextMessage producerMessage = producerSession
                .createTextMessage(text);

        // Send the message.
        producer.send(producerMessage);
        System.out.println("Message sent.");

        // Clean up the producer.
        producer.close();
        producerSession.close();
        producerConnection.close();
    }

    private static void receiveTopicMessage(ActiveMQConnectionFactory connectionFactory) throws JMSException {
        // Establish a connection for the consumer.
        // Note: Consumers should not use PooledConnectionFactory.
        final Connection consumerConnection = connectionFactory.createConnection();
        consumerConnection.start();
        //4. 创建会话
        Session session = consumerConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5. 创建一个目标
        Destination destination = session.createTopic("MyTopic");
        //6. 创建一个消费者
        MessageConsumer consumer = session.createConsumer(destination);
        // Receive the message when it arrives.
        // final TextMessage consumerTextMessage = (TextMessage) consumerMessage;
        //7. 创建一个监听器
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                try {
                    System.out.println("接收消息  = [" + ((TextMessage) message).getText() + "]");
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //System.out.println("Message received: " + consumerTextMessage.getText());

        // Clean up the consumer.
//        consumer.close();
//        session.close();
//        consumerConnection.close();
    }
}
