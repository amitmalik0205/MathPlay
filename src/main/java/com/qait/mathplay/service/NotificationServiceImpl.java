package com.qait.mathplay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qait.mathplay.dao.INotificationDao;
import com.qait.mathplay.dao.domain.Notification;

@Service("notificationService")
public class NotificationServiceImpl implements INotificationService {

	@Autowired
	private INotificationDao notificationDao;
	
	public void saveNotification(Notification notification) {
		notificationDao.create(notification);
	}
	
	@Override
	public List<Notification> getNotificationForUser(long userID) {
		return notificationDao.getNotificationForUser(userID);
	}
}
