package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Review implements Serializable, Comparable<Review> {
	private User user;
	private LocalDateTime postingDate;
	private int rating;
	private String comments;
//	private LinkedList<String> photos;

	public Review(User user, int rating, String comments) {
		this.user = user;
		this.rating = rating;
		postingDate = LocalDateTime.now();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getPostingDate() {
		return postingDate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

//	public LinkedList<String> getPhotos() {
//		return photos;
//	}
//
//	public void setPhotos(LinkedList<String> photos) {
//		this.photos = photos;
//	}

	@Override
	public int compareTo(Review review) {
		return this.postingDate.compareTo(review.getPostingDate());
	}

	@Override
	public String toString() {
		return "user=" + user.getUsername();
	}
	
}
