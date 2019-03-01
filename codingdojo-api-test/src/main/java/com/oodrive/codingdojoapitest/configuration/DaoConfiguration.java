package com.oodrive.codingdojoapitest.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.oodrive.codingdojoapitest.db.JdbcUserDao;
import com.oodrive.codingdojoapitest.db.MemoryUserDao;
import com.oodrive.codingdojoapitest.db.UserDao;
import com.oodrive.codingdojoapitest.models.User;
import com.oodrive.codingdojoapitest.models.UserData;


@Configuration
@AutoConfigureAfter(JdbcConfiguration.class)
public class DaoConfiguration {

	@Bean
	@ConditionalOnProperty(name = "db.mode", havingValue = "local")
	public UserDao localUserDao() {
		return new MemoryUserDao(createMockUsers());
	}

	@Bean
	@ConditionalOnProperty(name = "db.mode", havingValue = "postgres")
	public UserDao remoteUserDao(JdbcTemplate jdbcTemplate) {
		return new JdbcUserDao(jdbcTemplate);
	}

	private static List<User> createMockUsers() {
		List<User> users = new ArrayList<>();
		users.add(new User(1, new UserData("Ada", "Lovelace", 36)));
		users.add(new User(2, new UserData("Rich", "Hickey", 42)));
		users.add(new User(3, new UserData("Martin ", "Fowler", 56)));
		users.add(new User(4, new UserData("Alistair ", "Cockburn", 65)));
		users.add(new User(5, new UserData("James ", "Gosling", 63)));
		return users;
	}
}
