package com.qait.mathplay.service;

import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qait.mathplay.dao.IUserDao;
import com.qait.mathplay.dao.domain.User;
import com.qait.mathplay.dto.GroupMemberInfoDTO;
import com.qait.mathplay.rest.MathPlayNLearnServiceResponse;
import com.qait.mathplay.util.MathPlayPropertiesFileReaderUtil;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
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
		return userDao.getUserWithSecurityQuestion(userId);
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
