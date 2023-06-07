package natbra.appstarter.server.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="user_type")
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "usertype")
    private Set<User> users;

    public UserType() {

    }

    public UserType(Long id, String name, Set<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
