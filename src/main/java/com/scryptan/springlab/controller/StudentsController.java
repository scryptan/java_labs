package com.scryptan.springlab.controller;

import com.scryptan.springlab.entity.Student;
import com.scryptan.springlab.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/students")
public class StudentsController {

    final StudentService studentService;

    @Autowired
    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("{id}")
    public Student getStudent(@PathVariable int id){
        return studentService.getStudent(id);
    }

    @PostMapping()
    public Student saveStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }

    @PutMapping()
    public Student updateStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable int id){
        studentService.deleteStudent(id);
    }
}