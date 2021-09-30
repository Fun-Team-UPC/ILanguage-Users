package ilenguage.userservice.demo.query.projections;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class UserView {
    @Id @Column(length=36) @Getter @Setter
    private String userId;
    @Column(length=50) @Getter @Setter
    private String firstName;
    @Column(length=50) @Getter @Setter
    private String lastName;
    @Column(length=8, unique=true) @Getter @Setter
    private String dni;
    @Column(length=20) @Getter @Setter
    private String status;
    private Instant createdAt;
    @Column(nullable = true) @Getter @Setter
    private Instant updatedAt;

    public UserView(){

    }

    public UserView(String userId, String firstName,String lastName,String dni,String status,Instant createdAt){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.status = status;
        this.createdAt = createdAt;
    }
}
