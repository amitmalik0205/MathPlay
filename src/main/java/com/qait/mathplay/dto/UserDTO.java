package com.qait.mathplay.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1883116861275793394L;
	
	private long userKey;
	
	private String userID;

	private String name;
	
	private String city;
	
	private String country;

	public long getUserKey() {
		return userKey;
	}

	public void setUserKey(long userKey) {
		this.userKey = userKey;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
