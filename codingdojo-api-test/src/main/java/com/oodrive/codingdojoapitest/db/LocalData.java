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
}
