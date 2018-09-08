package microservices.training.camp.rest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Repository;

import microservices.training.camp.rest.bean.AppUser;

@Repository
@Transactional
@EntityScan("microservices.training.camp.rest.bean")
public class AppUserDaoService {
	
	@PersistenceContext
	private EntityManager entityManager;

	public AppUserDaoService(){}
	
	public AppUser getUserById(Long id) {
/*		Query query = entityManager.createNamedQuery("getUserById");
		query.setParameter("userId", id);
		return (AppUser)query.getSingleResult();*/
		return entityManager.find(AppUser.class, id);
	}

	public AppUser addUser(AppUser user) {
		entityManager.persist(user);
		return user;
	}
	
	public int deleteUserById(Long id){
		Query query = entityManager.createNamedQuery("deleteUserById");
		query.setParameter("userId", id);
		
		return query.executeUpdate();//returns 1 if object was successfully deleted
	}
	

	@SuppressWarnings("unchecked")
	public List<AppUser> getAllUsers() {
		return entityManager.createNamedQuery("getAllAppUsers").getResultList();
	}

}
