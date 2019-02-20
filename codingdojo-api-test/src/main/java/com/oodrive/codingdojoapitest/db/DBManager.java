package com.oodrive.codingdojoapitest.db;

import java.util.ArrayList;
import java.util.List;

import com.oodrive.codingdojoapitest.models.User;

public class DBManager {

	private List<User> users;

	private List<User> getUsers(){

		if(users == null){
			users = new ArrayList<>(5);

			users.add(new User(0, "Ada", "Lovelace", 36));
			users.add(new User(1, "Rich", "Hickey", 42));
			users.add(new User(2, "Martin ", "Fowler", 56));
			users.add(new User(3, "Alistair ", "Cockburn", 65));
			users.add(new User(4, "James ", "Gosling", 63));
		}

		return users;
	}

	public DojoData getData(String dbMode){

		switch(dbMode){
			case "local":
				return new LocalData(getUsers());
			case "postgres":
				throw new UnsupportedOperationException("Not implemented yet ;P !");
			default:
				throw new UnsupportedOperationException("Actually there are just DBManager.LOCAL and DBManager.POSTGRES as possible value");
		}
	}

}
