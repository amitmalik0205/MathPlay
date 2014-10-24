package com.qait.mathplay.dao;

import java.util.List;

import com.qait.mathplay.dao.domain.GameDetails;

public interface IGameDetailsDao extends IGenericDao<GameDetails> {
	
	public GameDetails getGameDetailsByUserAndGame(Long userID, Long gameId);
	
	public List<Object[]> getScoreForGroup(long groupID, long gameID);
	
	public List<Object[]> getTotalScoreForUser(long groupID);
}
