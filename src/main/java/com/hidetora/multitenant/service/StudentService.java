package com.hidetora.multitenant.service;

import com.hidetora.multitenant.entity.Student;

import java.util.List;

public interface StudentService {
    Student save(Student student);
    List<Student> getAllStudents();
    Student getStudent(Long id);
    Student getStudentByName(String name);

    void deleteStudentByName(String name);
}
