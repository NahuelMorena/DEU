package natbra.appstarter.server.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;
import java.util.List;

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


    public User() {}

    public User(long id, String username, String password, String email, Date birthdate, Integer telephone,
                String name, String surname, List<Role> roles) {
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
