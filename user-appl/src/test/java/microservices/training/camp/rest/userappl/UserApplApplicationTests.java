package microservices.training.camp.rest.userappl;

import java.util.List;

import junit.framework.Assert;
import microservices.training.camp.rest.bean.AppUser;
import microservices.training.camp.rest.dao.AppUserDaoService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplApplicationTests {
	Logger logger = LoggerFactory.getLogger(UserApplApplicationTests.class);
	
	@Autowired
	private AppUserDaoService appUserDaoService;
	
	@Test
	public void addUser() {
		appUserDaoService.addUser(new AppUser("Jaylorrrd1","password"));
		appUserDaoService.addUser(new AppUser("Jaylorrrd2","password"));
		
		Assert.assertTrue("Added Users", true);
	}
	
	@Test
	public void getUsers() {
		List<AppUser> userList = appUserDaoService.getAllUsers();
		logger.info("Users : \n" + userList);
		Assert.assertTrue(null != userList);
	}

}
