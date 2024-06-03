package ua.com.alevel.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.dto.response.ResponseContainer;
import ua.com.alevel.exception.InvalidApiVersionException;
import ua.com.alevel.service.ApiVersionService;

@Tag(name = "Api Version Page", description = "api version status operations")
@Validated
@RestController
@RequestMapping("/api/version")
@AllArgsConstructor
public class ApiVersionController {

    private final ApiVersionService apiVersionService;

    @GetMapping("current")
    public ResponseEntity<ResponseContainer<String>> current() {
        return ResponseEntity.ok(new ResponseContainer<>(apiVersionService.getApiVersion()));
    }

    @GetMapping("invalid")
    public void invalid() {
        throw new InvalidApiVersionException("Invalid api version");
    }
}
