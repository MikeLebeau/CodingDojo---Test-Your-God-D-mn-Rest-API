package com.oodrive.codingdojoapitest.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.oodrive.codingdojoapitest.db.DBManager;
import com.oodrive.codingdojoapitest.models.User;


@RestController
public class UserController {

	@GetMapping("/users")
	public List<User> getUsers(){
		return DBManager.getDataFrom(DBManager.LOCAL).getUsers();
	}

	@GetMapping("/users/{userId}")
	public User getOneUser(@PathVariable int userId){
		return DBManager.getDataFrom(DBManager.LOCAL).getUser(userId);
	}

}
