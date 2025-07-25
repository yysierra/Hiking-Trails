package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class HikingEntry implements Serializable, Comparable<HikingEntry>{
	private String trailName;
	private LocalDateTime postingDate;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private double distanceHiked;
	private String hikingDuration; 
	private double avgPace;
	private String comments;
	
	public HikingEntry(String trailName, LocalDateTime startDate, LocalDateTime endDate, double distanceHiked,
			String hikingDuration, double avgPace, String comments) {
		this.trailName = trailName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.distanceHiked = distanceHiked;
		this.hikingDuration = hikingDuration;
		this.avgPace = avgPace;
		this.comments = comments;
		postingDate = LocalDateTime.now();
	}

	public String getTrailName() {
		return trailName;
	}

	public void setTrailName(String trailName) {
		this.trailName = trailName;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public double getDistanceHiked() {
		return distanceHiked;
	}

	public void setDistanceHiked(double distanceHiked) {
		this.distanceHiked = distanceHiked;
	}

	public String getHikingDuration() {
		return hikingDuration;
	}

	public void setHikingDuration(String hikingDuration) {
		this.hikingDuration = hikingDuration;
	}

	public double getAvgPace() {
		return avgPace;
	}

	public void setAvgPace(double avgPace) {
		this.avgPace = avgPace;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public LocalDateTime getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(LocalDateTime postingDate) {
		this.postingDate = postingDate;
	}
	
	@Override
	public int compareTo(HikingEntry entry) {
		return this.postingDate.compareTo(entry.getPostingDate());
	}

	@Override
	public String toString() {
		return "Trail name: " + trailName;
	}

	
}
