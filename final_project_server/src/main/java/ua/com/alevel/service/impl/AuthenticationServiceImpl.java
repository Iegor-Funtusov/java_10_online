package ua.com.alevel.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ua.com.alevel.dto.request.AuthRequest;
import ua.com.alevel.dto.response.AuthResponse;
import ua.com.alevel.entity.user.RoleUser;
import ua.com.alevel.entity.user.User;
import ua.com.alevel.service.AuthenticationService;
import ua.com.alevel.service.JwtService;
import ua.com.alevel.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public AuthResponse register(AuthRequest authRequest) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        user.setEmail(authRequest.getEmail());
        user.setRole(RoleUser.ROLE_USER);
        userService.create(user);
        return getAuthResponse(user);
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        return getAuthResponse(userDetails);
    }

    private AuthResponse getAuthResponse(UserDetails userDetails) {
        final String token = jwtService.generateToken(userDetails);
        return new AuthResponse(token);
    }
}
