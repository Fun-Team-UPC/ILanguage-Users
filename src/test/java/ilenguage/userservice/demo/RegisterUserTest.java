package ilenguage.userservice.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ILenguage.user.contracts.commands.RegisterUser;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
public class RegisterUserTest{

    @Test
    @DisplayName("When register user REQUEST")
    public void registerRequest() {
        //arrange
        String userId = UUID.randomUUID().toString();
        Instant ocurredOn = Instant.now();
        //Act
        RegisterUser registerUser = new RegisterUser(userId,"test1","SWAGtest","75104902",ocurredOn);

        //Assert
        assertThat(registerUser);

    }

}
