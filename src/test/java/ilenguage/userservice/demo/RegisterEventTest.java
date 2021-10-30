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
    private FixtureConfiguration fixture;

    @BeforeEach
    public void setUp() throws Exception{
        fixture = new AggregateTestFixture(User.class);
    }

    @Test
    public void createRegister() throws Exception{
        String userId = UUID.randomUUID().toString();
        Instant now = Instant.now();
        RegisterUser registerUser = new RegisterUser(userId,"test1","SWAGtest","75104902");

        fixture.given()
                .when(registerUser = new RegisterUser(userId,"test2","SWAGtesting","75104909"))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new UserRegistered(registerUser.getUserId(),registerUser.getFirstName(),registerUser.getLastName(),registerUser.getDni(),now));

    }

    @Test
    public void createExistingUser() {
        String userId = UUID.randomUUID().toString();
        Instant now = Instant.now();
        RegisterUser registerUser = new RegisterUser(userId,"test1","SWAGtest","75104902");

        fixture.given(new UserRegistered(registerUser.getUserId(),registerUser.getFirstName(),registerUser.getLastName(),registerUser.getDni(),now))
                .when(new RegisterUser(userId,"test1","SWAGtest","75104902"))
                .expectException(EventStoreException.class);
    }

}