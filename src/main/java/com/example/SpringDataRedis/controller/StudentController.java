package com.example.SpringDataRedis.controller;

import com.example.SpringDataRedis.entity.Student;
import com.example.SpringDataRedis.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping(path = "/students")
    public List<Student> findAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping(path = "/{id}")
    public Student findStudent(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
