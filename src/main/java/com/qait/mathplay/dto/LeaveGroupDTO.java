package com.qait.mathplay.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class LeaveGroupDTO implements Serializable {

	private static final long serialVersionUID = -3762881306318486691L;

	@NotNull(message = "{LeaveGroupDTO.groupid.empty}")
	private Long groupID;
	
	@NotNull(message = "{LeaveGroupDTO.usedkey.empty}")
	private Long userKey;

	public Long getGroupID() {
		return groupID;
	}

	public void setGroupID(Long groupID) {
		this.groupID = groupID;
	}

	public Long getUserKey() {
		return userKey;
	}

	public void setUserKey(Long userKey) {
		this.userKey = userKey;
	}
}
