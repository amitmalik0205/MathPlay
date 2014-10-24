package com.qait.mathplay.dao;

import com.qait.mathplay.dao.domain.Game;

public interface IGameDao extends IGenericDao<Game> {

	public Game getGameByNameAndClass(String gameName, String gameClass);
}
