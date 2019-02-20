package com.oodrive.codingdojoapitest.db;

import java.util.ArrayList;
import java.util.List;

import com.oodrive.codingdojoapitest.models.User;

public class DBManager {

	public final static String LOCAL = "local";
	public final static String POSTGRES = "postgres";

	public static DojoData getDataFrom(String serverName){
		switch(serverName){
			case "local":
				List<User> users = new ArrayList<>(5);

				users.add(new User(0, "Ada", "Lovelace", 36));
				users.add(new User(1, "Rich", "Hickey", 42));
				users.add(new User(2, "Martin ", "Fowler", 56));
				users.add(new User(3, "Alistair ", "Cockburn", 65));
				users.add(new User(4, "James ", "Gosling", 63));

				return new LocalData(users);
			case "postgres":
				throw new UnsupportedOperationException("Not implemented yet ;P !");
			default:
				throw new UnsupportedOperationException("Actually there are just DBManager.LOCAL and DBManager.POSTGRES as possible value");
		}
	}

}
