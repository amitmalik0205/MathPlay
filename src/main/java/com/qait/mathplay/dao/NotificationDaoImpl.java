package com.qait.mathplay.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.qait.mathplay.dao.domain.Notification;

@Repository("notificationDao")
public class NotificationDaoImpl extends GenericDaoImpl<Notification> implements
		INotificationDao {

	public NotificationDaoImpl() {
		super(Notification.class);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Notification> getNotificationForUser(long userID) {
		Session session = getCurrentSession();
		List<Notification> list = new ArrayList<Notification>();
		String queryString = "FROM Notification n JOIN FETCH n.users u where u.id=:uid";
		Query query = session.createQuery(queryString);
		query.setParameter("uid", userID);
		list = query.list();
		return list;
	}
	
	@Override
	public void deleteNotification(List<Long> notificationIDArr, long userKey) {
		Session session = getCurrentSession();
		String queryString = "DELETE FROM user_notification where user_id=:uid and notification_id in (:nid)";
		Query query = session.createSQLQuery(queryString);
		query.setParameter("uid", userKey);
		query.setParameterList("nid", notificationIDArr);
		query.executeUpdate();
	}
	
	@Override
	public Notification getNotificationForGame(String gameName, String gameClass) {
		Session session = getCurrentSession();
		Notification notification = null;
		String queryString = "FROM Notification n where n.gameName = :gname and n.gameClass = :gclass";
		Query query = session.createQuery(queryString);
		query.setParameter("gname", gameName);
		query.setParameter("gclass", gameClass);
		notification = (Notification)query.uniqueResult();
		return notification;
	}
}
