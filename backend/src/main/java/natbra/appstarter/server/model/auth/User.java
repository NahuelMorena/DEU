package natbra.appstarter.server.model.auth;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
    private long id;

    private String username;
    private String password;
    
    @JoinTable(
        name = "user_roles", 
        joinColumns = { @JoinColumn(name = "id_user") }, 
        inverseJoinColumns = { @JoinColumn(name = "id_role") }
    )
    @OneToMany(fetch=FetchType.EAGER)
    private List<Role> roles;
    
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public User() {}

    public long getId() {
		return id;
	}

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }
    
    public List<Role> getRoles() {
        return this.roles;
    }
    
	public void setId(long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
    
}
