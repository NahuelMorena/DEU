package natbra.appstarter.server.model.train;

import natbra.appstarter.server.model.auth.User;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name="planifications")
public class Planification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    private String name;

    @OneToMany(mappedBy = "planification")
    private Set<TrainerPlanification> trainer_planification;
    

    public Planification(){

    }
    public Planification(Long id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
