package ua.com.alevel.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import static ua.com.alevel.util.ExceptionUtil.USER_EMAIL_IS_NOT_VALID;
import static ua.com.alevel.util.ValidatorsUtil.EMAIL_REGEX_PATTERN;

@Getter
@Setter
public class AuthRequest extends ApiRequest {

    @NotBlank(message = "email can't be blank")
    @Pattern(regexp = EMAIL_REGEX_PATTERN, message = USER_EMAIL_IS_NOT_VALID)
    private String email;

    @NotBlank(message = "password can't be blank")
    private String password;
}
