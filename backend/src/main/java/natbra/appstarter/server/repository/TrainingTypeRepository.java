package natbra.appstarter.server.repository;

import natbra.appstarter.server.model.train.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingTypeRepository extends JpaRepository<TrainingType, Long> {
}
