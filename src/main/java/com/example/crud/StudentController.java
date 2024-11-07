package com.example.crud;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // สร้างข้อมูลนักศึกษาใหม่
    @PostMapping("/add")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        Student student = new Student();
        Student savedStudent = studentRepository.save(student);
        
        StudentDTO responseDTO = new StudentDTO(savedStudent.getName(), savedStudent.getEmail());
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // อ่านข้อมูลนักศึกษาทั้งหมด
    @GetMapping("/all")
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(student -> new StudentDTO(student.getName(), student.getEmail()))
                .collect(Collectors.toList());
    }

    // อ่านข้อมูลนักศึกษาตาม ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        java.util.Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            StudentDTO studentDTO = new StudentDTO(student.get().getName(), student.get().getEmail());
            return new ResponseEntity<>(studentDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // แก้ไขข้อมูลนักศึกษา
    @PutMapping("/update/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDetails) {
        java.util.Optional<Student> student = studentRepository.findById(id);
        
        if (student.isPresent()) {
            Student updatedStudent = student.get();
            updatedStudent.setName(studentDetails.getName());
            updatedStudent.setEmail(studentDetails.getEmail());
            studentRepository.save(updatedStudent);
            
            StudentDTO responseDTO = new StudentDTO(updatedStudent.getName(), updatedStudent.getEmail());
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // ลบข้อมูลนักศึกษา
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Long id) {
        try {
            studentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
