package microservices.training.camp.rest.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonFilter;

@Entity(name="AppUser")
@JsonFilter(value="AppUserFilter")
@NamedQueries({@NamedQuery(name="getUserById", query="from AppUser where id := {0}"),
			   @NamedQuery(name="getAllUsers", query="from AppUser")})
public class AppUser {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Size(min=6, max=20)
	private String name;
	
	@Size(min=6, max=20)
	private String password;
	
	public AppUser(){
		
	}
	
	public AppUser(String name, String password) {
		super();
		this.name = name;
		this.password = password;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AppUser [id=" + id + ", name=" + name + ", password="
				+ password + "]";
	}
	
	
	
}
