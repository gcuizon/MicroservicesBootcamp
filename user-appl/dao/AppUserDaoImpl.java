package microservices.training.camp.rest.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import microservices.training.camp.rest.bean.AppUser;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class AppUserDaoImpl implements AppUserDao {
	
	@PersistenceContext
	EntityManagerFactory entityManager;

	@Override
	public AppUser getUserById(Long id) {
		// TODO Auto-generated method stub
		Query query = entityManager.createEntityManager().createNamedQuery("getUserById");
		query.setParameter(0, id);
		return (AppUser)query.getSingleResult();
	}

	@Override
	public void addUser(AppUser user) {
		entityManager.createEntityManager().persist(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AppUser> getAllUsers() {
		Query query = entityManager.createEntityManager().createNamedQuery("getAllUsers");
		return query.getResultList();
	}

}
