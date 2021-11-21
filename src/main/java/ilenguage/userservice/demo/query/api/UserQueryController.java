package ilenguage.userservice.demo.query.api;

import ilenguage.userservice.demo.query.api.projections.UserView;
import ilenguage.userservice.demo.query.api.projections.UserViewRepository;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api(tags = "Users")
public class UserQueryController {
    private final UserViewRepository userViewRepository;

    public UserQueryController(UserViewRepository userViewRepository){
        this.userViewRepository=userViewRepository;
    }

    @Operation(summary="Get all users", description="This endpoind returns all the availeble users for Ilanguage Application", tags = {"Users"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="All users returned", content = @Content(mediaType = "application/json",
                    schema= @Schema(example = "{\"userid\": \"54ac7365-3e3a-48cc-9747-fc776a98f572\",\"dni\": \"751049074\",\"firstname\": \"richard\",\"lastname\": \"alonzo\",\"status\": \"on\",\"update_at\": 2021-11-21T02:01:55.408Z}"))),
            @ApiResponse(responseCode = "404", description="Users Not Found", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("")
    public ResponseEntity<List<UserView>> getAllUsers(){
        try {
            return new ResponseEntity<List<UserView>>(userViewRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary="Get user by Id", description="This endpoind returns an specific user by the given ID Ilanguage Application", tags = {"Users"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="Users returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="Users Not Found", content = @Content(mediaType = "application/json"))

    })
    @RequestMapping(value= "id/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserView> getUserById(@PathVariable(name="id") String userId){
        try {
            return new ResponseEntity<UserView>(userViewRepository.getById(userId), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
