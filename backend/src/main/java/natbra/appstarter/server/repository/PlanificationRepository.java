package natbra.appstarter.server.repository;

import natbra.appstarter.server.model.train.Planification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PlanificationRepository extends JpaRepository<Planification, Long> {
    Set<Planification> findAllByUserId(Long userId);
}
