package com.codelab.backend.service;

import com.codelab.backend.model.Student;
import com.codelab.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
}
