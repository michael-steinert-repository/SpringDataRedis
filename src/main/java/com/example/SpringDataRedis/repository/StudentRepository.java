package com.example.SpringDataRedis.repository;

import com.example.SpringDataRedis.entity.Student;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.SpringDataRedis.config.RedisConst.HASH_KEY;

@Repository
@AllArgsConstructor
public class StudentRepository {
    private final RedisTemplate redisTemplate;

    public Student save(Student student) {
        redisTemplate.opsForHash().put(HASH_KEY, student.getId(), student);
        return student;
    }

    public List<Student> findAll() {
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public Student findById(Long id) {
        return (Student) redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    public void deleteById(Long id) {
        redisTemplate.opsForHash().delete(HASH_KEY, id);
    }
}
