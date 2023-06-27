package natbra.appstarter.server.model.train;

import com.fasterxml.jackson.annotation.JsonIgnore;
import natbra.appstarter.server.model.auth.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="califications")
public class Calification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer note;


    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "id_trainer_planification")
    private TrainerPlanification trainerPlanification;
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "id_training")
    private Training training;

    public Calification() {

    }

    public Calification(Long id, Integer note, User user, TrainerPlanification trainerPlanification, Training training) {
        this.id = id;
        this.note = note;
        this.user = user;
        this.trainerPlanification = trainerPlanification;
        this.training = training;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TrainerPlanification getTrainerPlanification() {
        return trainerPlanification;
    }

    public void setTrainerPlanification(TrainerPlanification trainerPlanification) {
        this.trainerPlanification = trainerPlanification;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
