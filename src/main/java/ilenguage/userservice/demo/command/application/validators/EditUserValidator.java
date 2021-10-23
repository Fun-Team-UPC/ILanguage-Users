package ilenguage.userservice.demo.command.application.validators;

import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

import ilenguage.common.application.Notification;
import ilenguage.userservice.demo.command.application.dto.request.EditUserRequest;
import ilenguage.userservice.demo.command.domain.User;
import ilenguage.userservice.demo.command.infra.UserDni;
import ilenguage.userservice.demo.command.infra.UserDniRepository;

import java.util.Optional;

@Component
public class EditUserValidator {
    private final UserDniRepository userDniRepository;
    private final Repository<User> userRepository;

    public EditUserValidator(UserDniRepository userDniRepository, Repository<User> userRepository) {
        this.userDniRepository = userDniRepository;
        this.userRepository = userRepository;
    }

    public Notification validate(EditUserRequest editUserRequest)
    {
        Notification notification = new Notification();
        String userId = editUserRequest.getUserId().trim();
        if (userId.isEmpty()) {
            notification.addError("User id is required");
        }
        loadUserAggregate(userId);
        String firstName = editUserRequest.getFirstName().trim();
        if (firstName.isEmpty()) {
            notification.addError("User firstname is required");
        }
        String lastName = editUserRequest.getLastName().trim();
        if (lastName.isEmpty()) {
            notification.addError("User lastname is required");
        }
        String dni = editUserRequest.getDni().trim();
        if (dni.isEmpty()) {
            notification.addError("User dni is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        Optional<UserDni> userDni = userDniRepository.getByDniForDistinctUserId(dni, userId);
        if (userDni.isPresent()) {
            notification.addError("User dni is taken");
        }
        return notification;
    }

    private void loadUserAggregate(String userId) {
        UnitOfWork unitOfWork = null;
        try {
            unitOfWork = DefaultUnitOfWork.startAndGet(null);
            userRepository.load(userId);
            unitOfWork.commit();
        } catch (AggregateNotFoundException ex) {
            unitOfWork.commit();
            throw ex;
        } catch(Exception ex) {
            if (unitOfWork != null) unitOfWork.rollback();
        }
    }
}