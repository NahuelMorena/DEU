package natbra.appstarter.server.controllers.requests;

import natbra.appstarter.server.model.train.TrainerPlanification;

import java.util.Set;

public class AddPlanification {
    private Set<TrainerPlanification> trainerPlanificationList;
    private String name;

    public AddPlanification(){}
    public AddPlanification(Set<TrainerPlanification> trainerPlanificationList, String name) {
        this.trainerPlanificationList = trainerPlanificationList;
        this.name = name;
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
