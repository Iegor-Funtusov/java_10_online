package ua.com.alevel.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import static ua.com.alevel.util.ExceptionUtil.USER_EMAIL_IS_NOT_VALID;
import static ua.com.alevel.util.ValidatorsUtil.EMAIL_REGEX_PATTERN;

@Getter
@Setter
@Schema(description = "authentication and registration request")
public class AuthRequest extends ApiRequest {

    @NotBlank(message = "email can't be blank")
    @Pattern(regexp = EMAIL_REGEX_PATTERN, message = USER_EMAIL_IS_NOT_VALID)
    @Schema(description = "authentication field: email (unique username)")
    private String email;

    @NotBlank(message = "password can't be blank")
    @Schema(description = "authentication field: password")
    private String password;
}
