package microservices.training.camp.rest.userappl.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import microservices.training.camp.rest.bean.AppUser;
import microservices.training.camp.rest.dao.AppUserDaoService;
import microservices.training.camp.rest.exception.ResquestResponse;
import microservices.training.camp.rest.exception.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class AppUserController {
	
	@Autowired
	private AppUserDaoService appUserDaoService;
	
	@Autowired
	private MessageSource messageSource;
	
	private SimpleBeanPropertyFilter userFilter;
	private FilterProvider filters;
	
	public AppUserController(){
		this.userFilter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name");
		this.filters = new SimpleFilterProvider().addFilter("AppUserFilter", this.userFilter);
		
	}
	
	/**
	 * Demo method to dynamically filter out output to users.
	 * @return
	 */
	@GetMapping(path="/users")
	public MappingJacksonValue getAllUsers() {
		
		List<AppUser> appUserList = appUserDaoService.getAllUsers();
		
 		MappingJacksonValue mapping = new MappingJacksonValue(appUserList);
 		mapping.setFilters(this.filters);
		
		return mapping;
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
		
		ResquestResponse requestResponse = 
				new ResquestResponse(new Date(), 
						this.messageSource.getMessage("user.deleted", new Long[]{id}, null), 
						null);
		return new ResponseEntity<Object>(requestResponse, HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping(path="/user/{id}")
	public MappingJacksonValue findUserById(@PathVariable Long id){
		AppUser user = appUserDaoService.getUserById(id);
		
		if(null == user){
			throw new UserNotFoundException(this.messageSource.getMessage("user.not.found", new Long[]{id}, null));
		}
			
		MappingJacksonValue mapping = new MappingJacksonValue(user);
		mapping.setFilters(this.filters);
		
		return mapping;
	}
}
