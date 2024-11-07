package com.example.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // Method to retrieve a student by ID
    public Optional<Student> getStudent(Long id) {
        return studentRepository.findById(id);
    }
}