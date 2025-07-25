package model;

import java.io.Serializable;
import java.util.TreeSet;

public class ReviewBag implements Serializable {
	private TreeSet<Review> reviews;
	
	public ReviewBag() {
		reviews = new TreeSet<>();
	}
	
	public void add(Review review) {
		reviews.add(review);
	}
	
	public TreeSet<Review> getReviews() {
		return reviews;
	}
	
	public void setReviews(TreeSet<Review> reviews) {
		this.reviews = reviews;
	}
	
	public void display() {
		reviews.forEach(System.out::println);
	}

	@Override
	public String toString() {
		return "reviews=" + reviews;
	}
	
}
