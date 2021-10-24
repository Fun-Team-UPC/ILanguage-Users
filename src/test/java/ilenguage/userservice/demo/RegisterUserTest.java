package ilenguage.userservice.demo;

import ilenguage.common.application.Notification;
import ilenguage.common.application.Result;
import ilenguage.userservice.demo.command.application.dto.response.RegisterUserResponse;
import ilenguage.userservice.demo.command.domain.User;
import ilenguage.userservice.demo.command.domain.UserStatus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ilenguage.userservice.demo.command.infra.*;
import ilenguage.userservice.demo.command.application.services.UserApplicationService;
import ilenguage.userservice.demo.command.application.dto.request.RegisterUserRequest;
import ILenguage.user.contracts.commands.RegisterUser;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class RegisterUserTest{

//    @Autowired
//    private UserApplicationService userApplicationService;

//    @Autowired
//    public RegisterUserRequest registerUserRequest;

    @Test
    @DisplayName("When register user REQUEST")
    public void registerRequest() {
        //arrange
        String userId = UUID.randomUUID().toString();
        User user = new User();

        //Act
        RegisterUser registerUser = new RegisterUser(userId,"test1","SWAGtest","75104902");

        //Assert
        assertThat(registerUser);

    }

}
