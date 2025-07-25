package model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Optional;
import java.util.TreeSet;

public class UserBag implements Serializable {
	private static UserBag instance;
	private TreeSet<User> users;
	
	private UserBag() {
		users = new TreeSet<>();
	}	
	
	public static UserBag getInstance() {
		if (instance == null) {
			instance = new UserBag();
		}
		return instance;
	}
	
	public TreeSet<User> getUsers() {
		return users;
	}
	
	public void setUserBag(UserBag users) {
		instance = users;
	}
	
	public void add(User user) {
		users.add(user);
	}
	
	public Optional<User> search(String username) {
		for (Iterator<User> bagIterator = users.iterator(); bagIterator.hasNext();) {
			User currentUser = bagIterator.next();
			if (currentUser.getUsername().equals(username)) {
				return Optional.ofNullable(currentUser);
			}
		}
		return Optional.ofNullable(null);
	}

	public void display() {
		users.forEach(System.out::println);
	}
	
}
