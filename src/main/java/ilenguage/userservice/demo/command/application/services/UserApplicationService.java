package ilenguage.userservice.demo.command.application.services;


import ilenguage.common.application.Notification;
import ilenguage.common.application.Result;
import ilenguage.common.application.ResultType;
import ilenguage.userservice.demo.command.application.dto.request.RegisterUserRequest;
import ilenguage.userservice.demo.command.application.dto.request.EditUserRequest;
import ilenguage.userservice.demo.command.application.dto.response.RegisterUserResponse;
import ilenguage.userservice.demo.command.application.dto.response.EditUserResponse;
import ilenguage.userservice.demo.command.application.validators.RegisterUserValidator;
import ilenguage.userservice.demo.command.application.validators.EditUserValidator;
import ilenguage.userservice.demo.command.infra.UserDniRepository;
import ILenguage.user.contracts.commands.RegisterUser;
import ILenguage.user.contracts.commands.EditUser;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class UserApplicationService {
    private final RegisterUserValidator registerUserValidator;
    private final EditUserValidator editUserValidator;
    private final CommandGateway commandGateway;
    private final UserDniRepository userDniRepository;

    public UserApplicationService(RegisterUserValidator registerUserValidator, EditUserValidator editUserValidator,CommandGateway commandGateway, UserDniRepository userDniRepository) {
        this.registerUserValidator = registerUserValidator;
        this.editUserValidator = editUserValidator;
        this.commandGateway = commandGateway;
        this.userDniRepository = userDniRepository;
    }

    public Result<RegisterUserResponse, Notification> register(RegisterUserRequest registerUserRequest) throws Exception {
        Notification notification = this.registerUserValidator.validate(registerUserRequest);


        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String userId = UUID.randomUUID().toString();
        Instant ocurredOn = Instant.now();

        RegisterUser registerUser = new RegisterUser(
                userId,
                registerUserRequest.getFirstName().trim(),
                registerUserRequest.getLastName().trim(),
                registerUserRequest.getDni().trim(),
                ocurredOn
        );
        CompletableFuture<Object> future = commandGateway.send(registerUser);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        RegisterUserResponse registerUserResponseDto = new RegisterUserResponse(
                registerUser.getUserId(),
                registerUser.getFirstName(),
                registerUser.getLastName(),
                registerUser.getDni()
        );
        return Result.success(registerUserResponseDto);
    }

    public Result<EditUserResponse, Notification> edit(EditUserRequest editUserRequest) throws Exception {

        Notification notification = this.editUserValidator.validate(editUserRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        EditUser editUser = new EditUser(
                editUserRequest.getUserId().trim(),
                editUserRequest.getFirstName().trim(),
                editUserRequest.getLastName().trim(),
                editUserRequest.getDni().trim()
        );
        CompletableFuture<Object> future = commandGateway.send(editUser);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        EditUserResponse editUserResponse = new EditUserResponse(
                editUser.getUserId(),
                editUser.getFirstName(),
                editUser.getLastName(),
                editUser.getDni()
        );
        return Result.success(editUserResponse);
    }

}
