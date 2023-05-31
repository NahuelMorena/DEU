package natbra.appstarter.server.model.auth;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@JoinTable(
		name = "authorities_roles", 
	    joinColumns = { @JoinColumn(name = "id_role") }, 
	    inverseJoinColumns = { @JoinColumn(name = "id_authority") }
	)
	@OneToMany(fetch=FetchType.EAGER)
	private List<Authority> authorities;
	
	public Role(Long id, String name, List<Authority> authorities) {
		super();
		this.id = id;
		this.name = name;
		this.authorities = authorities;
	}
	
	public Role() {}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public List<Authority> getAuthorities() {
		return authorities;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	
	
}
