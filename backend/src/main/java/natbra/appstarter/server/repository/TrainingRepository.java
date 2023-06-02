package natbra.appstarter.server.repository;


import natbra.appstarter.server.model.train.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {

}
