package com.qait.mathplay.service;

import java.util.List;

import com.qait.mathplay.dao.domain.GameDetails;

public interface IGameDetailsService {

	public void saveGameDetails(GameDetails details);
	
	public GameDetails getGameDetailsByUserAndGame(Long userID, Long gameId);
	
	public List<Object[]> getScoreForGroup(long groupID, long gameID);
	
	public List<Object[]> getTotalScoreForUser(long groupID);
}
