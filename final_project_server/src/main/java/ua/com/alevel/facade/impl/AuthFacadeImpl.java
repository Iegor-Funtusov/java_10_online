package ua.com.alevel.facade.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.request.AuthRequest;
import ua.com.alevel.entity.user.User;
import ua.com.alevel.facade.AuthFacade;
import ua.com.alevel.service.UserService;

@Service
@AllArgsConstructor
public class AuthFacadeImpl implements AuthFacade {

    private final UserService userService;

    @Override
    public void register(AuthRequest authRequest) {
        User user = new User();
//        user.setEmail(authRequest.getEmail());
//        user.setPassword(authRequest.getPassword());
        BeanUtils.copyProperties(authRequest, user);
        userService.create(user);
    }

    @Override
    public void login(String username, String password) {

    }
}
