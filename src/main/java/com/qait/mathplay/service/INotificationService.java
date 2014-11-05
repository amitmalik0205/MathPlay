package com.qait.mathplay.service;

import java.util.List;

import com.qait.mathplay.dao.domain.Notification;

public interface INotificationService {

	public void saveNotification(Notification notification);
	
	public List<Notification> getNotificationForUser(long userID);
}
