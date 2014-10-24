package com.qait.mathplay.service;

import com.qait.mathplay.dao.domain.Game;

public interface IGameService {

    public void saveGame(Game game);
	
	public Game getGameByNameAndClass(String gameName, String gameClass);
}
