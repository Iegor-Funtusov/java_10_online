package ua.com.alevel;

import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.com.alevel.entity.user.RoleUser;
import ua.com.alevel.entity.user.User;
import ua.com.alevel.service.JwtService;
import ua.com.alevel.service.UserService;

@SpringBootApplication
@AllArgsConstructor
public class FinalProjectServerApplication {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectServerApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    private void test() {
//        String password = "Test123!";
//        String email = "admin@test.com";
//
//        User user = new User();
//        user.setPassword(password);
//        user.setEmail(email);
//        user.setRole(RoleUser.ROLE_ADMIN);
//        user.setPassword(passwordEncoder.encode(password));
//        userService.create(user);
//
//        String token = jwtService.generateToken(user);
//        System.out.println("token = " + token);
//        String sha256 = DigestUtils.sha256Hex(password);
//        System.out.println("sha256 = " + sha256);
//
//        String sha256New = DigestUtils.sha256Hex(password);
//        System.out.println(sha256.equals(sha256New));
//
//        String encoder1 = passwordEncoder.encode("password1");
//        System.out.println("encoder1 = " + encoder1);
    }
}
