package com.toaosocial.rest.webservices.restfulwebservices.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;


@Entity
public class Post {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=10)
	private String description;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	

	public Post() {
		super();
	}

	public Post(Integer id, String description, User user) {
		super();
		this.id = id;
		this.description = description;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
