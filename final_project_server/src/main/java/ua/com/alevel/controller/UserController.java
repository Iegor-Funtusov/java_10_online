package ua.com.alevel.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.dto.response.ResponseContainer;
import ua.com.alevel.util.SecurityUtil;

@Tag(name = "User controller", description = "contains users methods")
@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    @GetMapping
    public ResponseEntity<ResponseContainer<String>> hello() {
        final String username = SecurityUtil.getCurrentUsername();
        return ResponseEntity.ok(new ResponseContainer<>("hello: " + username));
    }
}
