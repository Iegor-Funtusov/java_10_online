package ua.com.alevel.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.dto.request.AuthRequest;
import ua.com.alevel.dto.response.ResponseContainer;
import ua.com.alevel.facade.AuthFacade;

@Tag(name = "Authentication controller", description = "contains authentication methods")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthFacade authFacade;

    @PostMapping("/register")
    public ResponseEntity<ResponseContainer<Boolean>> register(
            @Valid
            @RequestBody
            AuthRequest authRequest) {
        authFacade.register(authRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseContainer<>(true));
    }
}
