package com.qait.mathplay.dao.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification implements Serializable {

	private static final long serialVersionUID = -5872228681379417635L;

	@Id
	@GeneratedValue
	@Column(name = "notification_id")
	private Long notificationID;

	@Column(name = "game_name", nullable = false)
	private String gameName;

	@Column(name = "game_class", nullable = false)
	private String gameClass;
	
	@Column(name = "new_user_name", nullable = false)
	private String newUserName;

	@Column(name = "new_user_city", nullable = false)
	private String newUserCity;

	@Column(name = "new_user_country", nullable = false)
	private String newUserCountry;

	@Column(name = "new_score", nullable = false)
	private long newScore;

/*	@Column(name = "old_user_name")
	private String oldUserName;

	@Column(name = "old_user_city")
	private String oldUserCity;

	@Column(name = "old_user_country")
	private String oldUserCountry;

	@Column(name = "old_score")
	private long oldScore;*/

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_notification", joinColumns = { @JoinColumn(name = "notification_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	private Set<User> users = new HashSet<User>();

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

/*	public String getOldUserName() {
		return oldUserName;
	}

	public void setOldUserName(String oldUserName) {
		this.oldUserName = oldUserName;
	}

	public String getOldUserCity() {
		return oldUserCity;
	}

	public void setOldUserCity(String oldUserCity) {
		this.oldUserCity = oldUserCity;
	}

	public String getOldUserCountry() {
		return oldUserCountry;
	}

	public void setOldUserCountry(String oldUserCountry) {
		this.oldUserCountry = oldUserCountry;
	}

	public long getOldScore() {
		return oldScore;
	}

	public void setOldScore(long oldScore) {
		this.oldScore = oldScore;
	}*/

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
