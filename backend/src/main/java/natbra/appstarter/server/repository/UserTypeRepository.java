package natbra.appstarter.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import natbra.appstarter.server.model.auth.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
}
