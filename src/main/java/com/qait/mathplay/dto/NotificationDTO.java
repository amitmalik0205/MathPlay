package com.qait.mathplay.dto;

import java.io.Serializable;

public class NotificationDTO implements Serializable {

	private static final long serialVersionUID = 4066788937064290139L;

	private Long notificationID;

	private String gameName;

	private String gameClass;
	
	private String newUserName;

	private String newUserCity;

	private String newUserCountry;

	private long newScore;

	public Long getNotificationID() {
		return notificationID;
	}

	public void setNotificationID(Long notificationID) {
		this.notificationID = notificationID;
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

	public String getNewUserName() {
		return newUserName;
	}

	public void setNewUserName(String newUserName) {
		this.newUserName = newUserName;
	}

	public String getNewUserCity() {
		return newUserCity;
	}

	public void setNewUserCity(String newUserCity) {
		this.newUserCity = newUserCity;
	}

	public String getNewUserCountry() {
		return newUserCountry;
	}

	public void setNewUserCountry(String newUserCountry) {
		this.newUserCountry = newUserCountry;
	}

	public long getNewScore() {
		return newScore;
	}

	public void setNewScore(long newScore) {
		this.newScore = newScore;
	}
}
