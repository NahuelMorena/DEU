package natbra.appstarter.server.model.auth;

import natbra.appstarter.server.model.train.Calification;
import natbra.appstarter.server.model.train.TrainerPlanification;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String email;
    private Date birthdate;
    private Integer telephone;
    private String name;
    private String surname;

    @JoinTable(
            name = "user_roles",
            joinColumns = { @JoinColumn(name = "id_user") },
            inverseJoinColumns = { @JoinColumn(name = "id_role") }
    )
    @OneToMany(fetch=FetchType.EAGER)
    private List<Role> roles;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "id_trainer")
    private User trainer;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "id_user_type")
    private UserType usertype;

    @OneToMany(mappedBy = "user",fetch=FetchType.EAGER)
    private Set<Calification> califications;

    public User() {}

    public User(long id, String username, String password, String email, Date birthdate, Integer telephone,
                String name, String surname, List<Role> roles, User trainer, UserType usertype) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthdate = birthdate;
        this.telephone = telephone;
        this.name = name;
        this.surname = surname;
        this.roles = roles;
        this.trainer = trainer;
        this.usertype = usertype;
    }

    public UserType getUsertype() {
        return usertype;
    }

    public void setUsertype(UserType user_type) {
        this.usertype = user_type;
    }

    public User getTrainer() {
        return trainer;
    }

    public void setTrainer(User trainer) {
        this.trainer = trainer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    
}
