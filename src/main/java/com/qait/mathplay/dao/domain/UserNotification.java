package com.qait.mathplay.dao.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*@Entity
@Table(name = "user_notification")*/
public class UserNotification implements Serializable {

	private static final long serialVersionUID = 3642484505623080670L;

	@Embeddable
	public static class UserNotificationID implements Serializable {

		private static final long serialVersionUID = 1L;

		@Column(name = "user_id")
		private Long userID;

		@Column(name = "notification_id")
		private Long notificationID;

		public UserNotificationID() { 
			
		}
		
		public UserNotificationID(Long userID, Long notificationID) {
			this.userID = userID;
			this.notificationID = notificationID;
		}

		@Override
		public boolean equals(Object o) {

			if (o != null && o instanceof UserNotificationID) {
				UserNotificationID that = (UserNotificationID) o;
				return this.userID.equals(that.userID)
						&& this.notificationID.equals(that.notificationID);
			} else {
				return false;
			}
		}

		@Override
		public int hashCode() {
			return userID.hashCode() + notificationID.hashCode();
		}

		public Long getUserID() {
			return userID;
		}

		public void setUserID(Long userID) {
			this.userID = userID;
		}

		public Long getNotificationID() {
			return notificationID;
		}

		public void setNotificationID(Long notificationID) {
			this.notificationID = notificationID;
		}
	}
	
	@EmbeddedId
	private UserNotificationID userNotificationID = new UserNotificationID();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "notification_id", insertable = false, updatable = false)
	private Notification notification;
	
	@Column(name = "notificationr_status", nullable=false)
	private Boolean notificationStatus;

	public UserNotification() {
		// TODO Auto-generated constructor stub
	}
	
	public UserNotification(User user, Notification notification, Boolean status) {
		this.user = user;
		this.notification = notification;
		this.notificationStatus = status;
		this.userNotificationID.userID = user.getId();
		this.userNotificationID.notificationID = notification.getNotificationID();
	}
}
