package natbra.appstarter.server.model.train;

import natbra.appstarter.server.model.auth.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="trainings")
public class Training {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date;

	private String name;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "id_training_type")
	private long id_training_type;

	private String description;

	private Integer warmup_time;

	private Integer repetitions_quantity;


    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

	public Training() {
		// TODO Auto-generated constructor stub
	}

	public Training(long id, LocalDate date, String name, long id_training_type, String description, Integer warmup_time, Integer repetitions_quantity, User user) {
		this.id = id;
		this.date = date;
		this.name = name;
		this.id_training_type = id_training_type;
		this.description = description;
		this.warmup_time = warmup_time;
		this.repetitions_quantity = repetitions_quantity;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId_training_type() {
		return id_training_type;
	}

	public void setId_training_type(long id_training_type) {
		this.id_training_type = id_training_type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getWarmup_time() {
		return warmup_time;
	}

	public void setWarmup_time(Integer warmup_time) {
		this.warmup_time = warmup_time;
	}

	public Integer getRepetitions_quantity() {
		return repetitions_quantity;
	}

	public void setRepetitions_quantity(Integer repetitions_quantity) {
		this.repetitions_quantity = repetitions_quantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
