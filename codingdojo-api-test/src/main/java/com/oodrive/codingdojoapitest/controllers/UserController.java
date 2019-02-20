package com.oodrive.codingdojoapitest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oodrive.codingdojoapitest.db.DBManager;
import com.oodrive.codingdojoapitest.models.User;


@RestController
public class UserController {

	@Value("${db.mode}")
	private String dbMode;

	private DBManager dbManager = new DBManager();

	@GetMapping("/users")
	public List<User> getUsers(){
		return dbManager.getData(dbMode).getUsers();
	}

	@GetMapping("/users/{userId}")
	public User getOneUser(@PathVariable int userId){
		return dbManager.getData(dbMode).getUser(userId);
	}

	@DeleteMapping("/users/{userId}")
	public User deleteOneUser(@PathVariable int userId){
		return dbManager.getData(dbMode).deleteUser(userId);
	}

	@PutMapping("/users/{userId}/disable")
	public User disableUser(@PathVariable int userId){
		return dbManager.getData(dbMode).disableUser(userId);
	}

	@PutMapping("/users/{userId}/enable")
	public User enableUser(@PathVariable int userId){
		return dbManager.getData(dbMode).enableUser(userId);
	}

}
