package ua.com.alevel.facade;

import ua.com.alevel.dto.request.AuthRequest;

public interface AuthFacade {

    void register(AuthRequest authRequest);
    void login(String username, String password);
}
