package com.example.SpringDataRedis.controller;

import com.example.SpringDataRedis.entity.Student;
import com.example.SpringDataRedis.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.SpringDataRedis.config.RedisConst.HASH_KEY;

@RestController
@RequestMapping("api/v1/student")
@AllArgsConstructor
@EnableCaching
public class StudentController {
    private final StudentService studentService;

    @GetMapping(path = "/students")
    public List<Student> findAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping(path = "/{id}")
    /* Do not caching Students that are older than 18 Years */
    @Cacheable(key = "#id", value = HASH_KEY, unless = "#result.age > 18")
    public Student findStudent(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PutMapping
    @CachePut(key = "#id", value = HASH_KEY)
    public Student updateStudent(@RequestBody Student student, Long id) {
        return studentService.updateStudent(student, id);
    }

    @DeleteMapping(path = "/{id}")
    @CacheEvict(key = "#id", value = HASH_KEY)
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
