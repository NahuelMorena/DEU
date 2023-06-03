package natbra.appstarter.server.repository;

import natbra.appstarter.server.model.train.TrainerPlanification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface TrainerPlanificationRepository extends JpaRepository<TrainerPlanification, Long> {
    //@Query("SELECT p FROM training_planifications p WHERE p.id_planification = :id_planification")
    //Set<TrainerPlanification> findAllById(@Param("id_planification") Long id_planification);

    Set<TrainerPlanification> findAllByPlanificationId(Long planificationId);

}
