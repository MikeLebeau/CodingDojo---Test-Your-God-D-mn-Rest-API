package com.oodrive.codingdojoapitest.db;

import java.util.List;

import com.oodrive.codingdojoapitest.models.User;


public interface DojoData {

	List<User> getUsers();
	User getUser(int id);
}
