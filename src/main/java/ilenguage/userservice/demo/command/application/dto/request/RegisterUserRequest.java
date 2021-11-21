package ilenguage.userservice.demo.command.application.dto.request;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class RegisterUserRequest {
    @Schema(type = "string", name = "firstname", example = "richard", minimum = "2", description = "testdesc")
    private String firstName;
    @Schema(type = "string", name = "lastName", example = "alonzo", minimum = "2", description = "testdesc")
    private String lastName;
    @Schema(type = "string", name = "dni", example = "75104907", minimum = "2", description = "testdesc")
    private String dni;

}
