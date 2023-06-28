package natbra.appstarter.server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import natbra.appstarter.server.model.auth.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

    Boolean existsByUsernameOrEmail(String username,String email);
    List<User> findAllByTrainerId(Long userId);
}
