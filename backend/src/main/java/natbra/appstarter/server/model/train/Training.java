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

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "training_type")
	private TrainingType training_type;

	private String description;

	private Integer warmup_time;

	private Integer repetitions_quantity;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "user")
    private User user;

	public Training() {
		// TODO Auto-generated constructor stub
	}

	public Training(long id, LocalDate date, String name, TrainingType training_type, String description, Integer warmup_time, Integer repetitions_quantity, User user) {
		this.id = id;
		this.date = date;
		this.name = name;
		this.training_type = training_type;
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

	public TrainingType getTraining_type() {
		return training_type;
	}

	public void setTraining_type(TrainingType training_type) {
		this.training_type = training_type;
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
