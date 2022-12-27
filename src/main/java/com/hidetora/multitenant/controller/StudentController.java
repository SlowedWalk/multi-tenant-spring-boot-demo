package com.hidetora.multitenant.controller;

import com.hidetora.multitenant.entity.Student;
import com.hidetora.multitenant.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/students/save")
    public ResponseEntity<?> save(@RequestBody Student student) {
        Student newStudent = studentService.save(student);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    @GetMapping("/students/all")
    public ResponseEntity<?> getAll() throws SQLException {
        List<Student> studentList = studentService.getAllStudents();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/students/{name}")
    public ResponseEntity<?> getStudentByName(@PathVariable String name) {
        Student student = studentService.getStudentByName(name);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/students/{name}")
    public ResponseEntity<?> deleteStudentByName(@PathVariable String name) {
        studentService.deleteStudentByName(name);
        return new ResponseEntity<>("Student " + name + " was deleted", HttpStatus.NO_CONTENT);
    }
}
