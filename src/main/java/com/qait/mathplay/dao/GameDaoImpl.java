package com.qait.mathplay.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.qait.mathplay.dao.domain.Game;

@Repository("gameDao")
public class GameDaoImpl extends GenericDaoImpl<Game> implements IGameDao {
	
	public GameDaoImpl() {
		super(Game.class);
	}
	
/*	@Override
	public boolean saveGame(Game game) {
		boolean isSaved = true;
		Session session = getCurrentSession();
		session.saveOrUpdate(game);
		return isSaved;
	}*/

	@Override
	public Game getGameByNameAndClass(String gameName, String gameClass) {
		Session session = getCurrentSession();
		Game game = null;
		String queryStr = "from Game where gameName=:gname and gameClass=:gclass";
		Query query = session.createQuery(queryStr);
		query.setParameter("gname", gameName);
		query.setParameter("gclass", gameClass);
		game = (Game)query.uniqueResult();
		return game;
	}
}
