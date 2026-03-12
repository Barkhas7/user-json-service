package com.barkhas.json.model;

public class UserProfile {
	private int id;
	private int userId;
	private String name;
	private String email;
	private String bio;
	private String phone;
	
	public UserProfile() {
	}
	
	public UserProfile(int id, int userId, String name, String email, String bio, String phone) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.bio = bio;
		this.phone = phone;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getBio() {
		return bio;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
}

