package model;

import java.io.Serializable;
import java.util.LinkedList;

public class TrailBag implements Serializable {
	private static TrailBag instance;
	private final static int MAX_CAPACITY = 50000;
	private LinkedList<Trail> trails;
	
	private TrailBag() {
		trails = new LinkedList<>();
	}
	
	public static TrailBag getInstance() {
		if (instance == null) {
			instance = new TrailBag();
		}
		return instance;
	}
	
	public void add(Trail trail) {
		if (trails.size() < MAX_CAPACITY) {
			trails.add(trail);
		}
		return;
	}
	
	public LinkedList<Trail> getTrailBag() {
		return trails;
	}
	
	public void setTrailBag(TrailBag trails) {
		instance = trails;
	}
	
}
