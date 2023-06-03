package natbra.appstarter.server.repository;

import natbra.appstarter.server.model.train.Planification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanificationRepository extends JpaRepository<Planification, Long> {
}
