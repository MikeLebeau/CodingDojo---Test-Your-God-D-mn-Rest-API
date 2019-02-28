package com.oodrive.codingdojoapitest.models;

public class User {

	private final int id;

	private final UserData userData;

	public User(int id, UserData userData) {
		this.id = id;
		this.userData = userData;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {return userData.getFirstName();}

	public String getLastName() {return userData.getLastName();}

	public int getAge() {return userData.getAge();}

	public boolean isActive() {return userData.isActive();}

	public User toggleActive() {
		UserData newData = new UserData(userData);
		newData.setActive(!newData.isActive());
		return new User(id, newData);
	}

}
