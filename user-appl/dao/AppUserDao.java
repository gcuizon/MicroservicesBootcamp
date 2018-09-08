package microservices.training.camp.rest.dao;

import java.util.List;

import microservices.training.camp.rest.bean.AppUser;

public interface AppUserDao {
	
	public AppUser getUserById(Long id);
	public void addUser(AppUser user);
	public List<AppUser> getAllUsers();

}
