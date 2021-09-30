package ilenguage.userservice.demo.command.application.dto.request;
import lombok.Value;

@Value
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String dni;
}
