package ilenguage.userservice.demo.command.api;

import ilenguage.common.api.ApiController;
import ilenguage.common.application.Notification;
import ilenguage.common.application.Result;

import ilenguage.userservice.demo.command.application.dto.request.RegisterUserRequest;
import ilenguage.userservice.demo.command.application.dto.request.EditUserRequest;
import ilenguage.userservice.demo.command.application.dto.response.RegisterUserResponse;
import ilenguage.userservice.demo.command.application.dto.response.EditUserResponse;
import ilenguage.userservice.demo.command.application.services.UserApplicationService;
import ilenguage.userservice.demo.command.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
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


    public UserCommandController(CommandGateway commandGateway, UserApplicationService userApplicationService) {
        this.commandGateway = commandGateway;
        this.userApplicationService = userApplicationService;
    }


    @Operation(summary="Save User", description="This endpoint is for saving a new user for Ilanguage Application", tags = {"Users"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="user register", content = @Content(mediaType = "application/json",
            schema = @Schema(example = "{\"userid\": \"54ac7365-3e3a-48cc-9747-fc776a98f572\",\"firstname\": \"richard\",\"lastname\": \"alonzo\",\"status\": \"on\",\"update_at\": 2021-11-21T02:01:55.408Z}"))),
            @ApiResponse(responseCode = "404", description="Users Not Found", content = @Content(mediaType = "application/json"))

    })
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

    @Operation(summary="Edit user", description="This endpoind is for editing an existing user in Ilanguage Application", tags = {"Users"} )
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
