package net.springboot.javaguides.service;

import net.springboot.javaguides.entity.Student;
import net.springboot.javaguides.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class StudentServiceTest {

    @InjectMocks
    StudentService service;

    @Mock
    StudentRepository repository;

    @Test
    public void getAll() {
        Student s1 = new Student("John DOE", "john.doe@gmail.com", 21526341);
        s1.setId(1);
        List<Student> dbData = new ArrayList<>();
        dbData.add(s1);

        // Simulation
        when(repository.findAll()).thenReturn(dbData);

        // Appel du service
        List<Student> result = service.getAll();

        // Assertions
        assertEquals(result.size(), dbData.size());
        assertEquals(result.get(0).getId(), dbData.get(0).getId());
        assertEquals(result.get(0).getEmail(), dbData.get(0).getEmail());
        assertEquals(result.get(0).getPhoneNo(), dbData.get(0).getPhoneNo());
        assertEquals(result.get(0).getName(), dbData.get(0).getName());
    }

    @Test
    public void updateStudentSucces() {
        long id = 10;
        Student dbStudent = new Student("John DOE", "john.doe@gmail.com", 21526341);
        dbStudent.setId(id);

        Student newStudent = new Student("John Phillip DOE", "john.doe@gmail.com", 21526341);
        newStudent.setId(id);

        Student param = new Student();
        param.setName("John Phillip DOE");

        // Simulation
        when(repository.findById(id)).thenReturn(Optional.of(dbStudent));
        when(repository.save(dbStudent)).thenReturn(newStudent);

        // Apppel fonction
        Student result = service.updateStudent(id, param);

        // Verification
        verify(repository).save(dbStudent);

        // Assertions
        assertEquals(result.getId(), id);
        assertEquals(result.getEmail(), dbStudent.getEmail());
        assertEquals(result.getName(), param.getName());
    }

    @Test
    public void updateStudentError() {
        long id = 10;
        long falseId = 11;
        Student dbStudent = new Student("John DOE", "john.doe@gmail.com", 21526341);
        dbStudent.setId(id);

        Student newStudent = new Student("John Phillip DOE", "john.doe@gmail.com", 21526341);
        newStudent.setId(id);

        Student param = new Student();
        param.setName("John Phillip DOE");

        // Simulation
        when(repository.findById(id)).thenReturn(Optional.of(dbStudent));
        when(repository.save(dbStudent)).thenReturn(newStudent);

        // Apppel fonction
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            Student result = service.updateStudent(falseId, param);
        });
    }
}