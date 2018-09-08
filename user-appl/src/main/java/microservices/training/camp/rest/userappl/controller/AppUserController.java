package microservices.training.camp.rest.userappl.controller;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import microservices.training.camp.rest.bean.AppUser;
import microservices.training.camp.rest.dao.AppUserDaoService;
import microservices.training.camp.rest.exception.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class AppUserController {
	
	@Autowired
	private AppUserDaoService appUserDaoService;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping(path="/users")
	public List<AppUser> getAllUsers() {
		return appUserDaoService.getAllUsers();
	}
	
	@PostMapping(path="/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody AppUser user){
		AppUser usr = appUserDaoService.addUser(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(usr.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path="/user/{id}")
	public ResponseEntity<Object> deleteUserById(@PathVariable Long id){
		int ret = appUserDaoService.deleteUserById(id);
		
		if(0 == ret){
			throw new UserNotFoundException(this.messageSource.getMessage("user.not.found", new Long[]{id}, null));
		}
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	
	@GetMapping(path="/user/{id}")
	public ResponseEntity<AppUser> findUserById(@PathVariable Long id){
		AppUser user = appUserDaoService.getUserById(id);
		
		if(null == user){
			throw new UserNotFoundException(this.messageSource.getMessage("user.not.found", new Long[]{id}, null));
		}
			
		return ResponseEntity.ok(user);
	}
}
