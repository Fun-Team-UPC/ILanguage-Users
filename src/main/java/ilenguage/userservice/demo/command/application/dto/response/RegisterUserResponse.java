package ilenguage.userservice.demo.command.application.dto.response;
import lombok.Value;

@Value
public class RegisterUserResponse {
    private String customerId;
    private String firstName;
    private String lastName;
    private String dni;
}
