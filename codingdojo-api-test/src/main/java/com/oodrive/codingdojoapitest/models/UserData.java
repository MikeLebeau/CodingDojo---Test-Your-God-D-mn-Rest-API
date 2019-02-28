package com.oodrive.codingdojoapitest.models;

import com.fasterxml.jackson.annotation.JsonCreator;


public class UserData {

	private String firstName;

	private String lastName;

	private int age;

	private boolean active;

	public UserData(String firstName, String lastName, int age) {
		this(firstName, lastName, age, true);
	}

	@JsonCreator
	public UserData(String firstName, String lastName, int age, boolean active) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.active = active;
	}

	public UserData(UserData userData) {
		this.firstName = userData.firstName;
		this.lastName = userData.lastName;
		this.age = userData.age;
		this.active = userData.active;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public boolean isActive() {
		return active;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "UserData{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", age=" + age +
				", active=" + active +
				'}';
	}
}
