package com.qait.mathplay.dto;

import java.io.Serializable;

import com.qait.mathlay.enums.MemberStatus;

public class GroupMemberInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long userKey;
	
	private String userID;
	
	private String name;
	
	private String city;
	
	private String country;
	
	private MemberStatus status;

	public GroupMemberInfoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public GroupMemberInfoDTO(long userKey, String userID, String name, String city, String country) {
		this.userKey = userKey;
		this.userID = userID;
		this.name = name;
		this.city = city;
		this.country = country;
		this.status = MemberStatus.ADD;
	}
	
	public GroupMemberInfoDTO(long userKey, String userID, String name, String city, String country, MemberStatus status) {
		this.userKey = userKey;
		this.userID = userID;
		this.name = name;
		this.city = city;
		this.country = country;
		this.status = status;
	}
	
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

	public MemberStatus getStatus() {
		return status;
	}

	public void setStatus(MemberStatus status) {
		this.status = status;
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
