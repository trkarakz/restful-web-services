package com.toaosocial.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.toaosocial.rest.webservices.restfulwebservices.exception.UserNotFoundException;

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
	public User reetrieveUser(@PathVariable Integer userId) {
		User retrievedUser = userDaoService.findUser(userId);
		
		if (retrievedUser == null) throw new UserNotFoundException("userId : " + userId);
		
		return retrievedUser;
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = userDaoService.saveUser(user);
		
		URI  location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
				
		return ResponseEntity.created(location).build();
		
	}
	
	
	
}
