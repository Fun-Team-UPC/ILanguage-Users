package ilenguage.userservice.demo;

import ilenguage.userservice.demo.command.domain.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ILenguage.user.contracts.commands.RegisterUser;


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
        RegisterUser registerUser = new RegisterUser(userId,"test1","SWAGtest","75104902");

//        fixture.given()
//                .when(new RegisterUser(registerUser.getUserId(),registerUser.getFirstName(),registerUser.getLastName(),registerUser.getDni()))
//                .expectSuccessfulHandlerExecution()
//                .expectEvents(new UserRegistered(registerUser.getUserId(),registerUser.getFirstName(),registerUser.getLastName(),registerUser.getDni(),));
    }

    @Test
    public void createExistingUser() {
        String userId = UUID.randomUUID().toString();
        RegisterUser registerUser = new RegisterUser(userId,"test1","SWAGtest","75104902");

//        fixture.given(new UserRegistered(registerUser.getUserId(),registerUser.getFirstName(),registerUser.getLastName(),registerUser.getDni(),))
//                .when(new RegisterUser(userId,"test1","SWAGtest","75104902"))
//                .expectException(EventStoreException.class);
    }

}