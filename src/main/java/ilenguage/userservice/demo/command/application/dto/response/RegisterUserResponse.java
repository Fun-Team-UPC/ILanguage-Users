package ilenguage.userservice.demo.command.application.dto.response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class RegisterUserResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String dni;
}
