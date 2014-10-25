package com.qait.mathplay.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class GroupScoreDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "{GroupScoreDTO.groupid.empty}")
	private Long groupID;
	
	@NotNull(message = "{GroupScoreDTO.gamename.empty}")
	@Length(min = 1, message = "{GroupScoreDTO.gamename.empty}")
	private String gameName;
	
	@NotNull(message = "{GroupScoreDTO.gameclass.empty}")
	@Length(min = 1, message = "{GameDetailsDTO.gameclass.empty}")
	private String gameClass;

	public Long getGroupID() {
		return groupID;
	}

	public void setGroupID(Long groupID) {
		this.groupID = groupID;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameClass() {
		return gameClass;
	}

	public void setGameClass(String gameClass) {
		this.gameClass = gameClass;
	}
	
	
}
