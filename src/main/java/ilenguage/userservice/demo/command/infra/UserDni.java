package ilenguage.userservice.demo.command.infra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDni {
    @Id
    @Column(length=8)
    public String dni;
    public String userId;

    public UserDni() {
    }

    public UserDni(String dni, String userId) {
        this.dni = dni;
        this.userId = userId;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
