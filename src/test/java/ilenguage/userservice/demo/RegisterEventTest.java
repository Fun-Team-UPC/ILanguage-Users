package ilenguage.userservice.demo;

import ILenguage.user.contracts.events.UserRegistered;
import ilenguage.userservice.demo.command.application.dto.request.RegisterUserRequest;
import ilenguage.userservice.demo.command.application.dto.response.RegisterUserResponse;
import io.cucumber.java.Before;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.Test;
import ilenguage.userservice.demo.command.application.services.UserApplicationService;
import ILenguage.user.contracts.commands.RegisterUser;
import ILenguage.user.contracts.commands.EditUser;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

public class RegisterEventTest {
    private FixtureConfiguration<UserApplicationService> fixture;


    private UserApplicationService userApplicationService;
    public RegisterUser registerUser;


    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(UserApplicationService.class);
    }

    @Test
    public void testFirstFixture() {
        String userId = UUID.randomUUID().toString();
        fixture.given(new RegisterUser(userId,"test1","SWAGtest","75104902"))
                .when(new RegisterUserResponse(registerUser.getUserId(),registerUser.getFirstName(),registerUser.getLastName(),registerUser.getDni()))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new RegisterUser(userId,"test1","SWAGtest","75104902"));
    }

}