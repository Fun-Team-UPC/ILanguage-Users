package ilenguage.userservice.demo.command.api;

import ilenguage.common.api.ApiController;
import ilenguage.common.application.Notification;
import ilenguage.common.application.Result;

import ilenguage.userservice.demo.command.application.dto.request.*;
import ilenguage.userservice.demo.command.application.dto.response.*;
import ilenguage.userservice.demo.command.application.services.UserApplicationService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/users")
@Api(tags = "Users")
public class UserCommandController {
    private final UserApplicationService userApplicationService;
    private final CommandGateway commandGateway;


    public UserCommandController(CommandGateway commandGateway,UserApplicationService userApplicationService) {
        this.commandGateway = commandGateway;
        this.userApplicationService = userApplicationService;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody RegisterUserRequest registerUserRequest) {

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
}