package com.toaosocial.rest.webservices.restfulwebservices.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Post {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String description;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	
}
