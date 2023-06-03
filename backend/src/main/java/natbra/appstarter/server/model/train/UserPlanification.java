package natbra.appstarter.server.model.train;

import natbra.appstarter.server.model.auth.User;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name="user_planifications")
public class UserPlanification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "id_planification")
    private Planification planification;

    private LocalDate date;

    public UserPlanification() {
    }

    public UserPlanification(Long id, User user, Planification planification, LocalDate date) {
        this.id = id;
        this.user = user;
        this.planification = planification;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
