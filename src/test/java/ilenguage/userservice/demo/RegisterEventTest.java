package ilenguage.userservice.demo;

import ILenguage.user.contracts.events.UserRegistered;
import ilenguage.userservice.demo.command.domain.*;
import org.axonframework.eventsourcing.eventstore.EventStoreException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ILenguage.user.contracts.commands.RegisterUser;

import java.time.Instant;
import java.util.UUID;

public class RegisterEventTest {
    private FixtureConfiguration<User> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(User.class);
    }

    @Test
    public void createRegister() throws Exception{
        String userId = UUID.randomUUID().toString();
        Instant ocurredOn = Instant.now();
        RegisterUser registerUser = new RegisterUser(userId,"testt222","SWAGtest","75104901",ocurredOn);

        UserRegistered userRegistered = new UserRegistered(registerUser.getUserId(),registerUser.getFirstName(),registerUser.getLastName(),registerUser.getDni(),ocurredOn);

        fixture.given()
                .when(registerUser)
                .expectEvents(userRegistered);

    }


    @Test
    public void createExistingUser() {
        String userId = UUID.randomUUID().toString();
        Instant ocurredOn = Instant.now();
        RegisterUser registerUser = new RegisterUser(userId,"test1","SWAGtest","75104902",ocurredOn);


        fixture.given(new UserRegistered(registerUser.getUserId(),registerUser.getFirstName(),registerUser.getLastName(),registerUser.getDni(),ocurredOn))
                .when(registerUser)
                .expectException(EventStoreException.class);
    }

}