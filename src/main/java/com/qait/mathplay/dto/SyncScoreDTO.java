package com.qait.mathplay.dto;

import java.io.Serializable;

public class SyncScoreDTO implements Serializable{

	private static final long serialVersionUID = 1317521731974474897L;

	private String gameName;
	
	private String gameClass;
	
	private long score;
	
	private String level;

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

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
