package com.oodrive.codingdojoapitest.db;

import java.util.List;

import com.oodrive.codingdojoapitest.models.User;


public class LocalData implements DojoData{

	private List<User> users;

	public LocalData(List<User> users) {
		this.users = users;
	}

	@Override
	public List<User> getUsers() {
		return users;
	}

	@Override
	public User getUser(int id) {
		return users.get(id);
	}

	@Override
	public User deleteUser(int id) {
		return users.remove(id);
	}

	@Override
	public User createUser(User user) {
		users.add(user);
		return user;
	}

	@Override
	public User disableUser(int id) {
		User user = users.get(id);
		user.setActive(false);
		return user;
	}

	@Override
	public User enableUser(int id) {
		User user = users.get(id);
		user.setActive(true);
		return user;
	}
}
