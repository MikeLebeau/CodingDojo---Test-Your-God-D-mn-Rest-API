package com.oodrive.codingdojoapitest.db;

import java.util.Collection;

import com.oodrive.codingdojoapitest.models.User;
import com.oodrive.codingdojoapitest.models.UserData;


public interface UserDao {

	Collection<User> getUsers();

	User getUser(int id);

	void deleteUser(int id);

	User createUser(UserData user);

	User disableUser(int id);

	User enableUser(int id);

}
