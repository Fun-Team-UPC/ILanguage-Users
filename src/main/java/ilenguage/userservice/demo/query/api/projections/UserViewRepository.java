package ilenguage.userservice.demo.query.api.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserViewRepository extends JpaRepository<UserView, String>{
    Optional<UserView> getByDni(String dni);

    @Query(value = "SELECT * FROM user_view WHERE user_id <> :userId AND dni = :dni",nativeQuery = true)
    Optional<UserView> getByUserIdAndDni(String userId, String dni);

    @Query(value = "SELECT * FROM user_view  WHERE user_id=?1",nativeQuery = true)
    public UserView getById(String userId);


}
