package com.qait.mathplay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qait.mathplay.dao.IUserDao;
import com.qait.mathplay.dao.domain.User;
import com.qait.mathplay.dto.GroupMemberInfoDTO;

@Service("userService")
public class UserServiceImpl implements IUserService {
		
	@Autowired
	private IUserDao userDao;
	
	
	public void saveUser(User user) {
		userDao.create(user);
	}
	
	@Override
	public User authenticateUser(String userId, String password) {
		return userDao.authenticateUser(userId, password);
	}
	
	@Override
	public List<GroupMemberInfoDTO> getMatchingUserID(String str) {
		return userDao.getMatchingUserID(str);
	}
	
	@Override
	public User getUserByUserId(String userId) {
		return userDao.getUserByUserId(userId);
	}
	
	@Override
	public User getUserWithSecurityQuestion(String userId) {
		List<User> list = userDao.getUserWithSecurityQuestion(userId);
		User user = null;
		if (list.size() > 0) {
			user = (User)list.get(0);
		}
		return user;
	}
	
	@Override
	public User getUser(long id) {
		return userDao.getUser(id);
	}
	
	@Override
	public List<GroupMemberInfoDTO> getMatchingUserIDForGroup(String str, long groupID) {
		return userDao.getMatchingUserIDForGroup(str, groupID);
	}
}
