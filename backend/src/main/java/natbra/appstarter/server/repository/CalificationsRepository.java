package natbra.appstarter.server.repository;

import natbra.appstarter.server.model.auth.User;
import natbra.appstarter.server.model.train.Calification;
import natbra.appstarter.server.model.train.TrainerPlanification;
import natbra.appstarter.server.model.train.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CalificationsRepository extends JpaRepository<Calification, Long> {

    Calification findByUserId(Long userId);

    Set<Calification> findAllByUserId(Long userId);

    boolean existsByUserAndTrainingAndTrainerPlanification(User user, Training training, TrainerPlanification trainerPlanification);
    Calification findByUserAndTrainingAndTrainerPlanification(User user, Training training, TrainerPlanification trainerPlanification);
}
