package ua.com.alevel.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.com.alevel.dto.request.EmployeeRequest;
import ua.com.alevel.dto.response.EmployeeResponse;
import ua.com.alevel.facade.EmployeeFacade;

import java.util.Collection;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Tag(name = "Employee crud", description = "the employees API with crud operations")
public class EmployeeController {

    private final EmployeeFacade employeeFacade;

    @GetMapping
    public ResponseEntity<Collection<EmployeeResponse>> getAllDepartments() {
        return ResponseEntity.ok(employeeFacade.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeFacade.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createDepartment(@RequestBody EmployeeRequest department) {
        employeeFacade.create(department);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDepartment(@RequestBody EmployeeRequest department, @PathVariable Long id) {
        employeeFacade.update(department, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        employeeFacade.delete(id);
        return ResponseEntity.ok().build();
    }
}
