package com.example.SpringDataRedis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

import static com.example.SpringDataRedis.config.RedisConst.HASH_KEY;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(HASH_KEY)
public class Student implements Serializable {
    @Id
    private Long id;
    private String name;
    private int age;
}