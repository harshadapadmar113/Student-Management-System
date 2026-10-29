
package com.hp.studentmanagement.service;

import com.hp.studentmanagement.model.Student;
import com.hp.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public boolean emailExists(String email) {
    	return studentRepository.existsByEmailIgnoreCase(email);
    }



    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public long getTotalStudents() {
    	return studentRepository.count();
    }

    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}

