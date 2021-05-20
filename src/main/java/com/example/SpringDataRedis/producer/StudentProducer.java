package com.example.SpringDataRedis.producer;

import com.example.SpringDataRedis.entity.Student;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StudentProducer {
    private final RedisTemplate redisTemplate;
    private final ChannelTopic channelTopic;

    @PostMapping("/produce")
    public void produceStudent(@RequestBody Student student) {
        redisTemplate.convertAndSend(channelTopic.getTopic(), student.toString());
    }

}
