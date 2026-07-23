package com.example.CRUD.service;
import com.example.CRUD.repository.StudentRepository;
import org.springframework.stereotype.Service;
import com.example.CRUD.entity.Student;
import java.util.List;
@Service
public  class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

}
