package com.oodrive.codingdojoapitest.db;

import java.util.List;

import com.oodrive.codingdojoapitest.models.User;


public interface DojoData {

	List<User> getUsers();
	User getUser(int id);
	User deleteUser(int id);
	User createUser(User user);
	User disableUser(int id);
	User enableUser(int id);
}
