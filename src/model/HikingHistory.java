package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.TreeSet;

public class HikingHistory implements Serializable {
	private TreeSet<HikingEntry> entries; 
	
	public HikingHistory() {
		entries = new TreeSet<>();
	}
	
	public void add(HikingEntry entry) {
		entries.add(entry);
	}

	public TreeSet<HikingEntry> getEntries() {
		return entries;
	}

	public void setEntries(TreeSet<HikingEntry> entries) {
		this.entries = entries;
	}
	
	public void display() {
		entries.forEach(System.out::println);
	}
	
	@Override
	public String toString() {
		return "entries=" + entries;
	}
	
}
