package model;

import java.io.Serializable;
import java.util.LinkedList;

public class Trail implements Serializable {
	private String trailName;
	private String headAddress;
	private double length; // (miles)
	private double elevationGain; // (feet)
	private String difficulty; // (easy, moderate, hard)
	private String trailType; // (loop, out and back, point-to-point)
	private ReviewBag reviews;

	public Trail(String trailName, String headAddress, double length, double elevationGain, String difficulty,
			String trailType) {
		this.trailName = trailName;
		this.headAddress = headAddress;
		this.length = length;
		this.elevationGain = elevationGain;
		this.difficulty = difficulty;
		this.trailType = trailType;
		reviews = new ReviewBag(); // dont know if i should set it equal to an already existing linkedlist, ill
										// come back to this later
	}

	public String getTrailName() {
		return trailName;
	}

	public void setTrailName(String trailName) {
		this.trailName = trailName;
	}

	public String getHeadAddress() {
		return headAddress;
	}

	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getElevationGain() {
		return elevationGain;
	}

	public void setElevationGain(double elevationGain) {
		this.elevationGain = elevationGain;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getTrailType() {
		return trailType;
	}

	public void setTrailType(String trailType) {
		this.trailType = trailType;
	}

	public ReviewBag getReviews() {
		return reviews;
	}

	public void setReviews(ReviewBag reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return trailName + ", " + headAddress + ", " + length + ", " + elevationGain + ", " + difficulty + ", "
				+ trailType;
	}

}
