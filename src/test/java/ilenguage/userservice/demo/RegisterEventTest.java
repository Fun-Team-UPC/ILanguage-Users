package ilenguage.userservice.demo;

import ILenguage.user.contracts.events.UserRegistered;
import ilenguage.userservice.demo.command.application.dto.request.RegisterUserRequest;
import ilenguage.userservice.demo.command.application.dto.response.RegisterUserResponse;
import ilenguage.userservice.demo.command.domain.User;
import io.cucumber.java.Before;
import org.axonframework.modelling.command.AggregateAnnotationCommandHandler;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.Test;
import ilenguage.userservice.demo.command.application.services.UserApplicationService;
import ILenguage.user.contracts.commands.RegisterUser;


import java.util.UUID;

public class RegisterEventTest {
    private FixtureConfiguration fixture;

    @Before
    public void setUp() throws Exception{
        fixture = new AggregateTestFixture<>(RegisterUser.class);
        AggregateAnnotationCommandHandler commandHandler = new AggregateAnnotationCommandHandler<>(RegisterUser.class, fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(commandHandler);
    }

    @Test
    public void testRegister() throws Exception{
        String userId = UUID.randomUUID().toString();
        RegisterUser registerUser = new RegisterUser(userId,"test1","SWAGtest","75104902");
        fixture.givenNoPriorActivity()
                .when(registerUser)
                .expectEvents(new RegisterUser(userId,"test1","SWAGtest","75104902"));

    }
//    @Test
//    public void testFirstFixture() {
//        String userId = UUID.randomUUID().toString();
//        fixture.given(new RegisterUser(userId,"test1","SWAGtest","75104902"))
//                .when(new RegisterUserResponse(registerUser.getUserId(),registerUser.getFirstName(),registerUser.getLastName(),registerUser.getDni()))
//                .expectSuccessfulHandlerExecution()
//                .expectEvents(new RegisterUser(userId,"test1","SWAGtest","75104902"));
//    }

}