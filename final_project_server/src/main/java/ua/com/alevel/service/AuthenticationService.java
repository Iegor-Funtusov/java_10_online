package ua.com.alevel.service;

import ua.com.alevel.dto.request.AuthRequest;
import ua.com.alevel.dto.response.AuthResponse;

public interface AuthenticationService {

    AuthResponse register(AuthRequest authRequest);
    AuthResponse authenticate(AuthRequest authRequest);
}
