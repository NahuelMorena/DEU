package natbra.appstarter.server.repository;


import natbra.appstarter.server.model.train.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findAllByUserId(Long userId);
}
