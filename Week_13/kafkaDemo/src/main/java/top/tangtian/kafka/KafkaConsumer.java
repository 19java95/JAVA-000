package top.tangtian.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author tangtian
 * @version 1.0
 * @className KafkaConsumer
 * @description
 * @date 2021/1/12 9:00 AM
 **/
@Component
public class KafkaConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);
    @KafkaListener(topics = {"myTopic"})
    public void listen(ConsumerRecord record){
        System.out.println(record.topic()+":"+record.value());
    }
}
