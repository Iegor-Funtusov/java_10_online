package ua.com.alevel.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.com.alevel.dto.request.DepartmentRequest;
import ua.com.alevel.dto.response.DepartmentResponse;
import ua.com.alevel.facade.DepartmentFacade;

import java.util.Collection;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
@Tag(name = "Department crud", description = "the department API with crud operations")
public class DepartmentController {

    private final DepartmentFacade departmentFacade;

    @GetMapping
    public ResponseEntity<Collection<DepartmentResponse>> getAllDepartments() {
        return ResponseEntity.ok(departmentFacade.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentFacade.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createDepartment(@RequestBody DepartmentRequest department) {
        departmentFacade.create(department);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDepartment(@RequestBody DepartmentRequest department, @PathVariable Long id) {
        departmentFacade.update(department, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentFacade.delete(id);
        return ResponseEntity.ok().build();
    }
}
