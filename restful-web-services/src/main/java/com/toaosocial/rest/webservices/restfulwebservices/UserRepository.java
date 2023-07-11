package com.toaosocial.rest.webservices.restfulwebservices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toaosocial.rest.webservices.restfulwebservices.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
