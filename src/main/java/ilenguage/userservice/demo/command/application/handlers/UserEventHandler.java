package ilenguage.userservice.demo.command.application.handlers;


import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import ILenguage.user.contracts.events.*;
import ilenguage.userservice.demo.command.infra.*;

@Component
@ProcessingGroup("userDni")
public class UserEventHandler {

    private final UserDniRepository userDniRepository;

    public UserEventHandler(UserDniRepository userDniRepository) {
        this.userDniRepository = userDniRepository;
    }
    @EventHandler
    public void on(UserRegistered event) {
       userDniRepository.save(new UserDni(event.getDni(), event.getUserId()));
    }

}
