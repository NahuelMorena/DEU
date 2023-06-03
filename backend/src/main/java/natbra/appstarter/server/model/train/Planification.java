package natbra.appstarter.server.model.train;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name="planifications")
public class Planification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "planification")
    private Set<TrainerPlanification> trainer_planification;
    

    public Planification(){

    }
    public Planification(Long id, String name) {
        this.id = id;
        this.name = name;
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
