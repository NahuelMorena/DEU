package natbra.appstarter.server.model.train;

import natbra.appstarter.server.model.auth.User;

import javax.persistence.*;

public class TrainerPlanification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "training")
    private Training training;


    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "planification")
    private Planification planification;

    private Integer order;

    private Integer duration;

    public TrainerPlanification(){

    }

    public TrainerPlanification(Long id, Training training, Planification planification, Integer order, Integer duration) {
        this.id = id;
        this.training = training;
        this.planification = planification;
        this.order = order;
        this.duration = duration;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
