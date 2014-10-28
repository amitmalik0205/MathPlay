package com.qait.mathplay.dto;

import java.io.Serializable;


public class GroupDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long groupID;

	private String groupName;
	
	private String ownerUserID;
	
	private String ownerName;
	
	private String ownerCity;
	
	private String ownerCountry;
	
	public GroupDTO(Long groupID, String groupName, String userID, String name,
			String city, String country) {
		this.groupID = groupID;
		this.groupName = groupName;
		this.ownerUserID = userID;
		this.ownerName = name;
		this.ownerCity = city;
		this.ownerCountry = country;
	}
	
	public GroupDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getGroupID() {
		return groupID;
	}

	public void setGroupID(Long groupID) {
		this.groupID = groupID;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getOwnerUserID() {
		return ownerUserID;
	}

	public void setOwnerUserID(String ownerUserID) {
		this.ownerUserID = ownerUserID;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerCity() {
		return ownerCity;
	}

	public void setOwnerCity(String ownerCity) {
		this.ownerCity = ownerCity;
	}

	public String getOwnerCountry() {
		return ownerCountry;
	}

	public void setOwnerCountry(String ownerCountry) {
		this.ownerCountry = ownerCountry;
	}
}
