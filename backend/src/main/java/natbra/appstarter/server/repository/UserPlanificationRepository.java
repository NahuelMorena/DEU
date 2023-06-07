package natbra.appstarter.server.repository;

import natbra.appstarter.server.model.train.UserPlanification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserPlanificationRepository extends JpaRepository<UserPlanification, Long> {
    Set<UserPlanification> findAllByPlanificationId(Long planificationId);
}
