package com.oodrive.codingdojoapitest.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oodrive.codingdojoapitest.db.UserDao;
import com.oodrive.codingdojoapitest.models.User;
import com.oodrive.codingdojoapitest.models.UserData;


@RestController
public class UserController {

	private final UserDao userDao;

	public UserController(UserDao userDao) {
		this.userDao = userDao;
	}

	@GetMapping("/users")
	public Collection<User> getUsers(@RequestParam(defaultValue = "", required = false) String sortBy) {
		return userDao.getUsers(sortBy);
	}

	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable int userId) {
		return userDao.getUser(userId);
	}

	@PutMapping("/users")
	public User createUser(@RequestBody UserData userData) {
		return userDao.createUser(userData);
	}

	@DeleteMapping("/users/{userId}")
	public void deleteUser(@PathVariable int userId) {
		userDao.deleteUser(userId);
	}

	@PatchMapping("/users/{userId}/disable")
	public User disableUser(@PathVariable int userId) {
		return userDao.disableUser(userId);
	}

	@PatchMapping("/users/{userId}/enable")
	public User enableUser(@PathVariable int userId) {
		return userDao.enableUser(userId);
	}

}
