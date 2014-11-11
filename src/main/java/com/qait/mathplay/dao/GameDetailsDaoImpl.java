package com.qait.mathplay.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.qait.mathlay.enums.MemberStatus;
import com.qait.mathplay.dao.domain.GameDetails;

@Repository("gameDetailsDao")
public class GameDetailsDaoImpl extends GenericDaoImpl<GameDetails> implements
		IGameDetailsDao {

	public GameDetailsDaoImpl() {
		super(GameDetails.class);
	}

	@Override
	public GameDetails getGameDetailsByUserAndGame(Long userID, Long gameId) {
		Session session = getCurrentSession();
		GameDetails details = null;
		String queryString = "from GameDetails g where g.user.id=:uid and g.game.gameId=:gameid";
		Query query = session.createQuery(queryString);
		query.setParameter("uid", userID);
		query.setParameter("gameid", gameId);
		details = (GameDetails) query.uniqueResult();
		return details;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getScoreForGroup(long groupID, long gameID) {
		List<Object[]> list = new ArrayList<Object[]>();
		Session session = getCurrentSession();
		String queryStr = "SELECT u.userID, u.name, u.city, u.country, gd.level, gd.userScore, gm.status from GroupMember gm JOIN gm.group g "
				+ "JOIN gm.member u JOIN u.gameDetails gd WHERE g.groupID = :gid and gd.game.gameId = :gameid and gm.status = :status"
				+ " ORDER BY gd.userScore DESC";
		Query query = session.createQuery(queryStr);
		query.setParameter("gid", groupID);
		query.setParameter("gameid", gameID);
		query.setParameter("status", MemberStatus.ACCEPTED);
		list = query.list();
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalScoreForUser(long groupID) {
		List<Object[]> list = new ArrayList<Object[]>();
		Session session = getCurrentSession();
		String queryStr = "SELECT u.userID, sum(gd.userScore) FROM GroupMember gm join gm.group g "
				+ "JOIN gm.member u JOIN u.gameDetails gd WHERE g.groupID = :gid GROUP BY u.userID ORDER BY sum(gd.userScore) DESC";
		Query query = session.createQuery(queryStr);
		query.setParameter("gid", groupID);
		list = query.list();
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getScoreForGroupAllGames(long groupID) {
		List<Object[]> list = new ArrayList<Object[]>();
		Session session = getCurrentSession();
		String queryStr = "SELECT gme.gameId, gme.gameName, gme.gameClass, u.id, u.userID, u.name, u.city, u.country, gd.level, gd.userScore "
				+ "from GroupMember gm JOIN gm.group g JOIN gm.member u JOIN u.gameDetails gd JOIN gd.game gme WHERE g.groupID = :gid "
				+ " ORDER BY gd.userScore DESC";
		Query query = session.createQuery(queryStr);
		query.setParameter("gid", groupID);
		list = query.list();
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getScoreForUserAllGames(long userID) {
		List<Object[]> list = new ArrayList<Object[]>();
		Session session = getCurrentSession();
		String queryStr = "SELECT gme.gameId, gme.gameName, gme.gameClass, u.id, u.userID, u.name, u.city, u.country, gd.level, gd.userScore "
				+ "from GameDetails gd JOIN gd.game gme JOIN gd.user u WHERE u.id = :uid ";
		Query query = session.createQuery(queryStr);
		query.setParameter("uid", userID);
		list = query.list();
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getUsereDetailsWithHighestScoreForGameInGroups(List<Long> groupIDList, long gameID) {
		List<Object[]> list = new ArrayList<Object[]>();
		Session session = getCurrentSession();
		String queryStr = "SELECT DISTINCT gd.userScore, u.id, u.userID, u.name, u.city, u.country from GroupMember gm JOIN gm.group g "
				+ " JOIN gm.member u JOIN u.gameDetails gd WHERE g.groupID in (:gid) and gd.game.gameId = :gameid and gm.status = :status "
				+ " ORDER BY gd.userScore desc";
		Query query = session.createQuery(queryStr);
/*		query.setFirstResult(0);
		query.setMaxResults(1);*/
		query.setParameterList("gid", groupIDList);
		query.setParameter("gameid", gameID);
		query.setParameter("status", MemberStatus.ACCEPTED);
		list = query.list();
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getUsereDetailsForGameInGroups(List<Long> groupIDList) {
		List<Object[]> list = new ArrayList<Object[]>();
		Session session = getCurrentSession();
		String queryStr = "SELECT DISTINCT u.id, u.userID, u.name, u.city, u.country from GroupMember gm JOIN gm.group g "
				+ " JOIN gm.member u WHERE g.groupID in (:gid) and gm.status = :status ";
		Query query = session.createQuery(queryStr);
		query.setParameterList("gid", groupIDList);
		query.setParameter("status", MemberStatus.ACCEPTED);
		list = query.list();
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<GameDetails> getAllGameDetailsForUser(long userID) {
		List<GameDetails> list = new ArrayList<GameDetails>();
		Session session = getCurrentSession();
		String queryStr = "FROM GameDetails gd JOIN FETCH gd.game g WHERE gd.user.id = :uid ";
		Query query = session.createQuery(queryStr);
		query.setParameter("uid", userID);
		list = query.list();
		return list;
	}
}
