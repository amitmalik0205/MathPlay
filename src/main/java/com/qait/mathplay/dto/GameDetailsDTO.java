package com.qait.mathplay.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class GameDetailsDTO implements Serializable {

	private static final long serialVersionUID = -6633852339257554017L;

	@NotNull(message = "{GameDetailsDTO.userScore.empty}")
	private Long userScore;
	
	@NotNull(message = "{GameDetailsDTO.level.empty}")
	@Length(min = 1, message = "{GameDetailsDTO.level.empty}")
	private String level;
	
	@NotNull(message = "{GameDetailsDTO.userid.empty}")
	@Length(min = 1, message = "{GameDetailsDTO.userid.empty}")
	private String userID;
	
	@NotNull(message = "{GameDetailsDTO.gamename.empty}")
	@Length(min = 1, message = "{GameDetailsDTO.gamename.empty}")
	private String gameName;
	
	@NotNull(message = "{GameDetailsDTO.gameclass.empty}")
	@Length(min = 1, message = "{GameDetailsDTO.gameclass.empty}")
	private String gameClass;
	
	private long gameID;
	
	private long userKey;
	
	private String name;
	
	private String city;
	
	private String country;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Long getUserScore() {
		return userScore;
	}

	public void setUserScore(Long userScore) {
		this.userScore = userScore;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
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

	public long getGameID() {
		return gameID;
	}

	public void setGameID(long gameID) {
		this.gameID = gameID;
	}

	public long getUserKey() {
		return userKey;
	}

	public void setUserKey(long userKey) {
		this.userKey = userKey;
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
