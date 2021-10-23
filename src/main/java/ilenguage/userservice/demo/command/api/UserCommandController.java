package ilenguage.userservice.demo.command.api;

import ilenguage.common.api.ApiController;
import ilenguage.common.application.Notification;
import ilenguage.common.application.Result;

import ilenguage.userservice.demo.command.application.dto.request.RegisterUserRequest;
import ilenguage.userservice.demo.command.application.dto.request.EditUserRequest;
import ilenguage.userservice.demo.command.application.dto.response.RegisterUserResponse;
import ilenguage.userservice.demo.command.application.dto.response.EditUserResponse;
import ilenguage.userservice.demo.command.application.dto.response.GetAllUsersResponse;
import ilenguage.userservice.demo.command.application.services.UserApplicationService;
import ilenguage.userservice.demo.command.domain.User;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@Api(tags = "Users")
public class UserCommandController {
    private final UserApplicationService userApplicationService;
    private final CommandGateway commandGateway;


    public UserCommandController(CommandGateway commandGateway, UserApplicationService userApplicationService) {
        this.commandGateway = commandGateway;
        this.userApplicationService = userApplicationService;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {

        try {
            Result<RegisterUserResponse, Notification> result = userApplicationService.register(registerUserRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }

    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> editUser(@PathVariable("userId") String userId, @RequestBody EditUserRequest editUserRequest) {
        try {
            editUserRequest.setUserId(userId);
            Result<EditUserResponse, Notification> result = userApplicationService.edit(editUserRequest);
            if (result.isSuccess()) {
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (AggregateNotFoundException exception) {
            return ApiController.notFound();
        } catch(Exception e) {
            return ApiController.serverError();
        }
    }

}
