package com.toaosocial.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.toaosocial.rest.webservices.restfulwebservices.UserRepository;
import com.toaosocial.rest.webservices.restfulwebservices.exception.UserNotFoundException;

import entity.User;
import jakarta.validation.Valid;

@RestController
public class UserJpaResource {

	private UserRepository userRepository;
	
	public UserJpaResource(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("jpa/users")
	public List<User> reetrieveAllUsers() {
		return userRepository.findAll();
		
	}
	
	@GetMapping("jpa/users/{userId}")
	public EntityModel<User> reetrieveUser(@PathVariable Integer userId) {
		Optional<User> retrievedUser = userRepository.findById(userId);
		
		if (retrievedUser.isEmpty()) throw new UserNotFoundException("userId : " + userId);
		
		EntityModel<User> entityModel = EntityModel.of(retrievedUser.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).reetrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
		
	}
	
	@DeleteMapping("jpa/users/{userId}")
	public void deleteUserById(@PathVariable Integer userId) {
		userRepository.deleteById(userId);
	}
	
	@PostMapping("jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		URI  location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
				
		return ResponseEntity.created(location).build();
		
	}
	
	
	
}
