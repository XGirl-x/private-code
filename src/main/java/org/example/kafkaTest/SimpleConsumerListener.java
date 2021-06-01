package org.example.kafkaTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

/**
 * 消息监听类（消息消费者）
 */
public class SimpleConsumerListener {
    private final static Logger logger = LoggerFactory.getLogger(SimpleConsumerListener.class);
    private final CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(id = "foo", topics = "topic-test")
    public void listen(byte[] records) {
        this.latch.countDown();
    }
}
