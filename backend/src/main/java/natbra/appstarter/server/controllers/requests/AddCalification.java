package natbra.appstarter.server.controllers.requests;

public class AddCalification {

    private Long user_id;
    private Integer note;
    private Long training_id;
    private Long trainer_planification_id;
    public AddCalification(){}

    public AddCalification(Long user_id, Integer note, Long training_id,Long trainer_planification_id) {
        this.user_id = user_id;
        this.note = note;
        this.training_id = training_id;
        this.trainer_planification_id = trainer_planification_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Long getTraining_id() {
        return training_id;
    }

    public void setTraining_id(Long training_id) {
        this.training_id = training_id;
    }

    public Long getTrainer_planification_id() {
        return trainer_planification_id;
    }

    public void setTrainer_planification_id(Long trainer_planification_id) {
        this.trainer_planification_id = trainer_planification_id;
    }
}

