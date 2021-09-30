package ilenguage.userservice.demo.query.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ilenguage.userservice.demo.query.projections.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Api(tags = "Users")
public class UserQueryController {
    private final UserViewRepository userViewRepository;

    public UserQueryController(UserViewRepository userViewRepository){
        this.userViewRepository=userViewRepository;
    }



}
