package net.springboot.javaguides.service;

import net.springboot.javaguides.entity.Student;
import net.springboot.javaguides.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll() {
        return this.studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        return this.studentRepository.save(student);
    }

    public Student updateStudent(long id, Student student) {
        Student oldStudent = this.studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student id : " + id));

        if(student.getEmail() != null){
            oldStudent.setEmail(student.getEmail());
        }

        if(student.getName() != null){
            oldStudent.setName(student.getName());
        }

        if(student.getPhoneNo() != null){
            oldStudent.setPhoneNo(student.getPhoneNo());
        }
        // update student
        return studentRepository.save(oldStudent);
    }

    public void deleteStudent(long id) {
        Student student = this.studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student id : " + id));
        this.studentRepository.delete(student);
    }
}
