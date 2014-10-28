package com.qait.mathplay.service;

import java.util.List;
import java.util.Map;

import com.qait.mathplay.dao.domain.GameDetails;
import com.qait.mathplay.dto.UserGameScoreDTO;

public interface IGameDetailsService {

	public void saveGameDetails(GameDetails details);
	
	public GameDetails getGameDetailsByUserAndGame(Long userID, Long gameId);
	
	public List<Object[]> getScoreForGroup(long groupID, long gameID);
	
	public List<Object[]> getTotalScoreForUser(long groupID);
	
	public List<UserGameScoreDTO> getScoreForGroupAllGames(long groupID);
	
	public Map<Long, List<UserGameScoreDTO>> getScoreForEachGameByGroupForAllUsers(long groupID);
	
	public List<UserGameScoreDTO> getScoreForUserAllGames(long userID);
	
	public Map<Long, Long> getScoreForEachGameForUser(long userID);
}
