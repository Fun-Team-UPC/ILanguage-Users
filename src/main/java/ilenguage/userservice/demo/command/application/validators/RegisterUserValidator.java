package ilenguage.userservice.demo.command.application.validators;

import ilenguage.userservice.demo.command.application.dto.request.*;
import ilenguage.userservice.demo.command.infra.UserDni;
import ilenguage.userservice.demo.command.infra.UserDniRepository;
import org.springframework.stereotype.Component;
import ilenguage.common.application.Notification;

import java.util.Optional;

@Component
public class RegisterUserValidator {
    private final UserDniRepository userDniRepository;

    public RegisterUserValidator(UserDniRepository userDniRepository) {
        this.userDniRepository = userDniRepository;
    }

    public Notification validate(RegisterUserRequest registerUserRequest)
    {
        Notification notification = new Notification();
        String firstName = registerUserRequest.getFirstName().trim();
        if (firstName.isEmpty()) {
            notification.addError("User firstname is required");
        }
        String lastName = registerUserRequest.getLastName().trim();
        if (lastName.isEmpty()) {
            notification.addError("User lastname is required");
        }
        String dni = registerUserRequest.getDni().trim();
        if (dni.isEmpty()) {
            notification.addError("User dni is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        Optional<UserDni> userDniOptional = userDniRepository.findById(dni);
        if (userDniOptional.isPresent()) {
            notification.addError("User dni is taken");
        }
        return notification;
    }
}
