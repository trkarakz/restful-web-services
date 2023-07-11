package com.toaosocial.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import entity.User;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<User>();
	
	private static int usersCount = 1;
	
	static {
		users.add(new User(usersCount++, "Yahya", LocalDate.now().minusYears(10)));
		users.add(new User(usersCount++, "Meryem", LocalDate.now().minusYears(7)));
		users.add(new User(usersCount++, "Zülfikar", LocalDate.now().minusYears(45)));
		users.add(new User(usersCount++, "Burcu", LocalDate.now().minusYears(45)));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User findUser(Integer userId) {
		Predicate<? super User> predicate = user -> user.getId().equals(userId);
		return users.stream().filter(predicate).findFirst().orElse(null); 
	}

	public User saveUser(User user) {
		user.setId(usersCount++);
		users.add(user);
		return user;
		
	}

	public void deleteUserById(Integer userId) {
		Predicate<? super User> predicate = user -> user.getId().equals(userId);
		users.removeIf(predicate);
		
	}
}
