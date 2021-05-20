package com.example.SpringDataRedis.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

@Slf4j
public class StudentConsumer implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] bytes) {
        log.info(String.format("Consumed Event: %s", message));
    }
}
