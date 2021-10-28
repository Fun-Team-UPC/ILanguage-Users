package ilenguage.userservice.demo.query.api.projections;

import ilenguage.userservice.demo.command.domain.UserStatus;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;
import ILenguage.user.contracts.events.*;

import java.time.Instant;

@Component
public class UserViewProjection {
    private final UserViewRepository userViewRepository;

    public UserViewProjection(UserViewRepository userViewRepository){
        this.userViewRepository = userViewRepository;
    }

    @EventHandler
    public void on(UserRegistered event,@Timestamp Instant timestamp){
        UserView userView = new UserView(event.getUserId(),event.getFirstName(),event.getLastName(),event.getDni(), UserStatus.ACTIVE.toString(),event.getOccurredOn());
        userViewRepository.save(userView);
    }

}
