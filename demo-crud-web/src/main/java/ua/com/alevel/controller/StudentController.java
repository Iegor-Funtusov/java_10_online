package ua.com.alevel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.entity.Student;
import ua.com.alevel.service.StudentService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping()
    public ResponseEntity<Boolean> create(@RequestBody Student student) {
        studentService.create(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@RequestBody Student student, @PathVariable Long id) {
        studentService.update(student, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }
}
