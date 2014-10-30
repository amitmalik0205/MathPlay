package com.qait.mathplay.dto;

import java.io.Serializable;

public class UserRankResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 4899921346994883823L;

	private String gameName;
	
	private String gameClass;
	
	private long score;
	
	private int rank;

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

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}
}
