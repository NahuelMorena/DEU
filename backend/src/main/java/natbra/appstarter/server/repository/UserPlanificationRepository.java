package natbra.appstarter.server.repository;

import natbra.appstarter.server.model.train.UserPlanification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface UserPlanificationRepository extends JpaRepository<UserPlanification, Long> {
    //@Query("SELECT p FROM user_planifications p WHERE p.id_planification = :id_planification")
    //Set<UserPlanification> findAllById(@Param("id_planification") Long id_planification);
}
