package com.toaosocial.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.toaosocial.rest.webservices.restfulwebservices.exception.UserNotFoundException;

import entity.User;
import jakarta.validation.Valid;

@RestController
public class UserResource {

	private UserDaoService userDaoService;
	
	public UserResource(UserDaoService userDaoService) {
		this.userDaoService = userDaoService;
	}
	
	@GetMapping("/users")
	public List<User> reetrieveAllUsers() {
		return userDaoService.findAll();
		
	}
	
	@GetMapping("/users/{userId}")
	public EntityModel<User> reetrieveUser(@PathVariable Integer userId) {
		User retrievedUser = userDaoService.findUser(userId);
		
		if (retrievedUser == null) throw new UserNotFoundException("userId : " + userId);
		
		EntityModel<User> entityModel = EntityModel.of(retrievedUser);
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).reetrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
		
	}
	
	@DeleteMapping("/users/{userId}")
	public void deleteUserById(@PathVariable Integer userId) {
		userDaoService.deleteUserById(userId);
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.saveUser(user);
		
		URI  location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
				
		return ResponseEntity.created(location).build();
		
	}
	
	
	
}
