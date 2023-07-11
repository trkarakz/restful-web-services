package com.toaosocial.rest.webservices.restfulwebservices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toaosocial.rest.webservices.restfulwebservices.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
