package ua.com.alevel.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.request.AuthRequest;
import ua.com.alevel.dto.response.AuthResponse;
import ua.com.alevel.dto.response.ResponseContainer;
import ua.com.alevel.facade.AuthFacade;
import ua.com.alevel.service.AuthenticationService;

@Tag(name = "Authentication controller", description = "contains authentication methods")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ResponseContainer<AuthResponse>> register(
            @Valid
            @RequestBody
            AuthRequest authRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseContainer<>(authenticationService.register(authRequest)));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseContainer<AuthResponse>> login(
            @Valid
            @RequestBody
            AuthRequest authRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseContainer<>(authenticationService.authenticate(authRequest)));
    }
}
