package com.qait.mathplay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qait.mathplay.dao.IGameDao;
import com.qait.mathplay.dao.domain.Game;

@Service("gameService")
public class GameServiceImpl implements IGameService {
	
	@Autowired
	private IGameDao gameDao;

	@Override
	public void saveGame(Game game) {
		gameDao.create(game);
	}

	@Override
	public Game getGameByNameAndClass(String gameName, String gameClass) {
		return gameDao.getGameByNameAndClass(gameName, gameClass);
	}
}
