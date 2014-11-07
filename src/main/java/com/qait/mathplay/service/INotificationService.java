package com.qait.mathplay.service;

import java.util.List;

import com.qait.mathplay.dao.domain.Notification;

public interface INotificationService {

	public void saveNotification(Notification notification);
	
	public List<Notification> getNotificationForUser(long userID);
	
	public void deleteUserNotification(List<Long> notificationIDArr, long userKey);
	
	public Notification getNotificationForGame(String gameName, String gameClass);
}
