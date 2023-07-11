package com.toaosocial.rest.webservices.restfulwebservices;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
