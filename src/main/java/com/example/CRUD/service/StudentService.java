package com.example.CRUD.service;

import com.example.CRUD.entity.Student;
import com.example.CRUD.exception.ResourceNotFoundException;
import com.example.CRUD.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {
        logger.info("Creating new student with email: {}", student.getEmail());
        Student saved = studentRepository.save(student);
        logger.debug("Student created with id: {}", saved.getId());
        return saved;
    }

    public List<Student> getAllStudents() {
        logger.debug("Fetching all students");
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        logger.debug("Fetching student with id: {}", id);
        return studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Student not found with id: {}", id);
                    return new ResourceNotFoundException("Student not found with id " + id);
                });
    }

    public Student updateStudent(Long id, Student studentDetails) {
        logger.info("Updating student with id: {}", id);
        Student student = getStudentById(id);
        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setCourse(studentDetails.getCourse());
        return studentRepository.save(student);
    }

    public Student patchStudent(Long id, Student studentDetails) {
        logger.info("Patching student with id: {}", id);
        Student student = getStudentById(id);

        if (studentDetails.getName() != null) {
            student.setName(studentDetails.getName());
        }
        if (studentDetails.getEmail() != null) {
            student.setEmail(studentDetails.getEmail());
        }
        if (studentDetails.getCourse() != null) {
            student.setCourse(studentDetails.getCourse());
        }

        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        logger.info("Deleting student with id: {}", id);
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }
}