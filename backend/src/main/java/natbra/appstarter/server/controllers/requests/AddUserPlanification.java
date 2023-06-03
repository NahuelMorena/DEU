package natbra.appstarter.server.controllers.requests;

import natbra.appstarter.server.model.auth.User;
import natbra.appstarter.server.model.train.Planification;
import natbra.appstarter.server.model.train.TrainerPlanification;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.util.Set;

public class AddUserPlanification {

    private Set<User> users;

    private Planification planification;

    private LocalDate date;

    public AddUserPlanification(){

    }
    public AddUserPlanification(Set<User> users, Planification planification, LocalDate date) {
        this.users = users;
        this.planification = planification;
        this.date = date;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Planification getPlanification() {
        return planification;
    }

    public void setPlanification(Planification planification) {
        this.planification = planification;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
