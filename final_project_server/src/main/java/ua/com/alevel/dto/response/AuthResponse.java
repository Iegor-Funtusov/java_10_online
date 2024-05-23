package ua.com.alevel.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "authentication response")
public class AuthResponse {

    @Schema(description = "authentication token")
    private String token;
}
