package model;

import java.io.Serializable;
import java.util.LinkedList;

public class User implements Serializable, Comparable<User> {
	private String username;
	private String password;
	private String phoneNumber;
	private boolean isAdmin;
	private LinkedList<User> followers;
	private LinkedList<User> following;
	private LinkedList<User> blocked;
	private HikingHistory entries;
	private ReviewBag reviews;
	
	public User(String username, String password, String phoneNumber) {
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		followers = new LinkedList<>();
		following = new LinkedList<>();
		blocked = new LinkedList<>();
		entries = new HikingHistory();
		reviews = new ReviewBag();
		isAdmin = false;
	}
	
	public LinkedList<User> getFollowers() {
		return followers;
	}
	
	public LinkedList<User> getFollowing() {
		return following;
	}
	
	public LinkedList<User> getBlocked() {
		return blocked;
	}

	public void setBlocked(LinkedList<User> blocked) {
		this.blocked = blocked;
	}

	public String getUsername() {
		return username;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public HikingHistory getEntries() {
		return entries;
	}

	public void setEntries(HikingHistory entries) {
		this.entries = entries;
	}
	
	public ReviewBag getReviews() {
		return reviews;
	}

	public void setReviews(ReviewBag reviews) {
		this.reviews = reviews;
	}
	
	@Override
	public int compareTo(User user) {
		return this.username.compareTo(user.getUsername());
	}

	@Override
	public String toString() {
		return "User [" + username + "]";
	}

	
}
