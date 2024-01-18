package ua.com.alevel.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.entity.Student;
import ua.com.alevel.repository.StudentRepository;
import ua.com.alevel.service.StudentService;

import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public void create(Student entity) {
        studentRepository.save(entity);
    }

    @Override
    public void update(Student entity, Long id) {
        checkExist(id);
        Student student = getById(id);
        student.setFirstName(entity.getFirstName());
        student.setLastName(entity.getLastName());
        student.setAge(entity.getAge());
        studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        checkExist(id);
        studentRepository.deleteById(id);
    }

    @Override
    public Student findById(Long id) {
        return getById(id);
    }

    @Override
    public Collection<Student> findAll() {
        return studentRepository.findAll();
    }

    private void checkExist(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found");
        }
    }

    private Student getById(Long id) {
        return studentRepository.findById(id).get();
    }
}
