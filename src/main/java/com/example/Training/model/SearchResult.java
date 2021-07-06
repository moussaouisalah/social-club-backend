package com.example.Training.model;

public class SearchResult {
	private int id;
	private String type;
	private String name;
	private String image;
	
	public SearchResult(int id, String type, String name, String image) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.image = image;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
