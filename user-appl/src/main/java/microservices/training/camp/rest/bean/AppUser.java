package microservices.training.camp.rest.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;

@Entity
//@JsonFilter("AppUserFilter")
@NamedQueries({
			   @NamedQuery(name="getUserById", query="FROM AppUser WHERE id=:userId"), 
               @NamedQuery(name="getAllAppUsers", query="FROM AppUser"),
               @NamedQuery(name="deleteUserById", query="DELETE FROM AppUser WHERE id=:userId")
})
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
