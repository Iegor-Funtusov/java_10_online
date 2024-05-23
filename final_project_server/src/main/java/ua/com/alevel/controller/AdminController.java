package ua.com.alevel.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.dto.response.ResponseContainer;

@Tag(name = "Admin controller", description = "contains admin methods")
@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    @GetMapping
    public ResponseEntity<ResponseContainer<String>> hello() {
        return ResponseEntity.ok(new ResponseContainer<>("hello admin"));
    }
}
