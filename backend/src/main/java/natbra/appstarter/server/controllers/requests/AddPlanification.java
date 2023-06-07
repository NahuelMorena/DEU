package natbra.appstarter.server.controllers.requests;

import natbra.appstarter.server.model.train.TrainerPlanification;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

public class AddPlanification {
    private Set<TrainerPlanification> trainerPlanificationList;
    private String name;
    @Id
    private Long id;
    public AddPlanification(){}
    public AddPlanification(Set<TrainerPlanification> trainerPlanificationList, String name, Long id) {
        this.trainerPlanificationList = trainerPlanificationList;
        this.name = name;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<TrainerPlanification> getTrainerPlanificationList() {
        return trainerPlanificationList;
    }

    public void setTrainerPlanificationList(Set<TrainerPlanification> trainerPlanificationList) {
        this.trainerPlanificationList = trainerPlanificationList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
