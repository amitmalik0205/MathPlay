package com.qait.mathplay.dto;

import java.io.Serializable;

public class GetInvitationsDTO implements Serializable {

	private static final long serialVersionUID = -2608060206184340410L;

	private String groupName;

	private long groupID;

	private String groupOwner;

	private String ownerName;

	private String ownerCity;

	private String ownerCountry;
	
	private long memberID;

	public GetInvitationsDTO(String groupName, long groupID, String groupOwner,
			String name, String city, String country, long memberID) {
		this.groupName = groupName;
		this.groupID = groupID;
		this.groupOwner = groupOwner;
		this.ownerName = name;
		this.ownerCity = city;
		this.ownerCountry = country;
		this.memberID = memberID;
	}

	public GetInvitationsDTO(String groupName, long groupID) {
		this.groupName = groupName;
		this.groupID = groupID;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public long getGroupID() {
		return groupID;
	}

	public void setGroupID(long groupID) {
		this.groupID = groupID;
	}

	public String getGroupOwner() {
		return groupOwner;
	}

	public void setGroupOwner(String groupOwner) {
		this.groupOwner = groupOwner;
	}

	public long getMemberID() {
		return memberID;
	}

	public void setMemberID(long memberID) {
		this.memberID = memberID;
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
