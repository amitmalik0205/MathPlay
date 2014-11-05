package com.qait.mathplay.dao;

import java.util.List;

import com.qait.mathplay.dao.domain.Notification;

public interface INotificationDao extends IGenericDao<Notification> {

	public List<Notification> getNotificationForUser(long userID); 
}
