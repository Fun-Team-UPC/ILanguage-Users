package ilenguage.userservice.demo.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserDniRepository extends JpaRepository<UserDni, String>{
    Optional<UserDni> getDniByUserId(String userId);

    @Query(value = "SELECT * FROM user_dni WHERE user_id <> :userId AND dni = :dni LIMIT 1",nativeQuery = true )
    Optional<UserDni> getByDniForDistinctUserId(String dni, String userId);

}
