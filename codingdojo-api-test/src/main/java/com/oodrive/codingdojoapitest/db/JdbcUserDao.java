package com.oodrive.codingdojoapitest.db;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.oodrive.codingdojoapitest.models.User;
import com.oodrive.codingdojoapitest.models.UserData;


public class JdbcUserDao implements UserDao {

	private final JdbcTemplate jdbcTemplate;

	private final RowMapper<User> userRowMapper = (resultSet, rowNum) -> {
		int id = resultSet.getInt("id");
		String firstName = resultSet.getString("firstName");
		String lastName = resultSet.getString("lastName");
		int age = resultSet.getInt("age");
		boolean active = resultSet.getBoolean("active");
		return new User(id, new UserData(firstName, lastName, age, active));
	};

	@Autowired
	public JdbcUserDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	@Transactional
	public List<User> getUsers(String sortBy) {
		String sql = "SELECT * FROM my_user";

		switch(sortBy){
			case "firstName":
				sql += " ORDER BY firstName";
				break;
			case "lastName":
				sql += " ORDER BY lastName";
				break;
			case "age":
				sql += " ORDER BY age";
				break;
			case "active":
				sql += " ORDER BY active";
				break;
			default:
				break;
		}

		return new ArrayList<>(jdbcTemplate.query(sql, userRowMapper));
	}

	@Override
	public User getUser(int id) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM my_user WHERE id = ?", userRowMapper, id);
		} catch(EmptyResultDataAccessException e) {
			throw NotFoundException.create(User.class, id);
		}
	}

	@Override
	public User createUser(UserData userData) {
		String sql = "INSERT INTO my_user (firstName, lastName, age, active) VALUES (?, ?, ?, ?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		//generatedKeyHolder.getKeyList().add(Collections.singletonMap("id", Integer.class));

		jdbcTemplate.update(connection -> {
			PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, userData.getFirstName());
			pst.setString(2, userData.getLastName());
			pst.setInt(3, userData.getAge());
			pst.setBoolean(4, userData.isActive());
			return pst;
		}, keyHolder);
		int id = (int) keyHolder.getKeys().get("id");
		return new User(id, userData);
	}

	@Override
	public void deleteUser(int id) {
		jdbcTemplate.update("DELETE FROM my_user WHERE id = ?", id);
	}

	@Override
	public User disableUser(int id) {
		User user = getUser(id);
		return toggleActive(user, false);
	}

	@Override
	public User enableUser(int id) {
		User user = getUser(id);
		return toggleActive(user, true);
	}

	private User toggleActive(User user, boolean nextState) {
		if(user.isActive() != nextState) {
			User newUser = user.toggleActive();
			if(jdbcTemplate.update("UPDATE my_user SET active = ? WHERE id = ?", nextState, user.getId()) == 0)
				throw new ConcurrentModificationException("User has " + user.getId() + " been deleted");
			return newUser;
		}
		return user;
	}

}
