package com.oodrive.codingdojoapitest.db;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.oodrive.codingdojoapitest.models.User;
import com.oodrive.codingdojoapitest.models.UserData;


public class MemoryUserDao implements UserDao {

	private final ConcurrentMap<Integer, User> users;

	private final AtomicInteger lastUserId = new AtomicInteger();

	public MemoryUserDao(List<User> users) {
		Map<Integer, User> mappedUsers = users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
		this.users = new ConcurrentHashMap<>(mappedUsers);
		OptionalInt maxId = users.stream().mapToInt(User::getId).max();
		maxId.ifPresent(lastUserId::set);
	}

	@Override
	public Collection<User> getUsers() {
		return Collections.unmodifiableCollection(users.values());
	}

	@Override
	public User getUser(int id) {
		User user = users.get(id);
		if(user == null)
			throw NotFoundException.create(User.class, id);
		return user;
	}

	@Override
	public void deleteUser(int id) {
		users.remove(id);
	}

	@Override
	public User createUser(UserData userData) {
		int id = lastUserId.incrementAndGet();
		User user = new User(id, userData);
		users.put(id, user);
		return user;
	}

	@Override
	public User disableUser(int id) {
		return toggleActive(id, false);
	}

	@Override
	public User enableUser(int id) {
		return toggleActive(id, true);
	}

	private User toggleActive(int id, boolean nextState){
		User user = users.get(id);
		if(user == null)
			throw NotFoundException.create(User.class, id);

		if(user.isActive() != nextState){
			User newUser = user.toggleActive();
			users.replace(id, newUser);
			return newUser;
		}

		return user;
	}

}
