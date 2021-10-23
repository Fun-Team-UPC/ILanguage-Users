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

    @GetMapping("")
    public ResponseEntity<List<UserView>> getAllUsers(){
        try {
            return new ResponseEntity<List<UserView>>(userViewRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value= "id/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserView> getUserById(@PathVariable(name="id") String userId){
        try {
            return new ResponseEntity<UserView>(userViewRepository.getById(userId), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value= "id/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<UserView> deleteById(@PathVariable(name="id") String userId){
        try {
             return new ResponseEntity<UserView>(userViewRepository.delById(userId), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
