package ilenguage.userservice.demo.command.domain;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import ILenguage.user.contracts.commands.*;
import ILenguage.user.contracts.events.*;
import java.time.Instant;

@Aggregate
public class User {
    @AggregateIdentifier
    private String userId;
    private String firstName;
    private String lastName;
    private String dni;
    private Instant occurredOn;

    public User(){

    }

    @CommandHandler
    public User(RegisterUser command){

        apply(
                new UserRegistered(
                    command.getUserId(),
                        command.getFirstName(),
                        command.getLastName(),
                        command.getDni(),
                        command.getOccurredOn()
                )
        );
    }

    @EventSourcingHandler
    protected void on(UserRegistered event) {
        userId = event.getUserId();
        firstName = event.getFirstName();
        lastName = event.getLastName();
        dni = event.getDni();
        UserStatus status = UserStatus.ACTIVE;
    }

}

