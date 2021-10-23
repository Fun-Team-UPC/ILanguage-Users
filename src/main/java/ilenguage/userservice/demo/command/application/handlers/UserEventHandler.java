package ilenguage.userservice.demo.command.application.handlers;


import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import ILenguage.user.contracts.events.UserRegistered;
import ilenguage.userservice.demo.command.infra.UserDniRepository;
import ilenguage.userservice.demo.command.infra.UserDni;

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
