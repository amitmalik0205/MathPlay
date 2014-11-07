package com.qait.mathplay.dto;

import java.io.Serializable;
import java.util.List;

public class DeleteNotificationDTO implements Serializable {

	private static final long serialVersionUID = -2833918178008226742L;
	
	private List<Long> notificationIDArr;
	
	private long userKey;

	public List<Long> getNotificationIDArr() {
		return notificationIDArr;
	}

	public void setNotificationIDArr(List<Long> notificationIDArr) {
		this.notificationIDArr = notificationIDArr;
	}

	public long getUserKey() {
		return userKey;
	}

	public void setUserKey(long userKey) {
		this.userKey = userKey;
	}
}
