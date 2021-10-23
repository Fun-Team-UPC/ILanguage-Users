package ilenguage.userservice.demo.command.application.dto.request;

import ilenguage.userservice.demo.command.domain.UserStatus;
import lombok.Getter;
import lombok.Setter;

public class EditUserRequest {
    private @Setter
    @Getter
    String userId;
    private @Getter String firstName;
    private @Getter String lastName;
    private @Getter String dni;
}
