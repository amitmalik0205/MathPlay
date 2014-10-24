package com.qait.mathplay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qait.mathplay.dao.IGameDetailsDao;
import com.qait.mathplay.dao.domain.GameDetails;

@Service("gameDetailsService")
public class GameDetailsServiceImpl implements IGameDetailsService {

	@Autowired
	private IGameDetailsDao gameDetailsDao;
	
	@Override
	public void saveGameDetails(GameDetails details) {
		  gameDetailsDao.saveOrUpdate(details);
	}
	
	@Override
	public GameDetails getGameDetailsByUserAndGame(Long userID, Long gameId) {
		return gameDetailsDao.getGameDetailsByUserAndGame(userID, gameId);
	}
	
	@Override
	public List<Object[]> getScoreForGroup(long groupID, long gameID) {
		return gameDetailsDao.getScoreForGroup(groupID, gameID);
	}
	
	@Override
	public List<Object[]> getTotalScoreForUser(long groupID) {
		return gameDetailsDao.getTotalScoreForUser(groupID);
	}
}
