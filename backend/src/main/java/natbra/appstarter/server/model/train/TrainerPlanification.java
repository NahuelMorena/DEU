package natbra.appstarter.server.model.train;

import natbra.appstarter.server.model.auth.User;

import javax.persistence.*;
@Entity
@Table(name="trainer_planifications")
public class TrainerPlanification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "id_training")
    private Training training;


    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "id_planification")
    private Planification planification;

    private Integer orderNumber;

    private Integer minutes;

    public TrainerPlanification(){

    }

    public TrainerPlanification(Long id, Training training, Planification planification, Integer orderNumber, Integer minutes) {
        this.id = id;
        this.training = training;
        this.planification = planification;
        this.orderNumber = orderNumber;
        this.minutes = minutes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Planification getPlanification() {
        return planification;
    }

    public void setPlanification(Planification planification) {
        this.planification = planification;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }


}
