package natbra.appstarter.server.model.train;


import natbra.appstarter.server.model.auth.Authority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="training_type")
public class TrainingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public TrainingType() {
    }

    public TrainingType(Long id, String name) {
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
