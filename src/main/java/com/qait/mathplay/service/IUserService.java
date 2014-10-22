package com.qait.mathplay.service;

import java.util.List;

import com.qait.mathplay.dao.domain.User;
import com.qait.mathplay.dto.GroupMemberInfoDTO;

public interface IUserService {

	public void saveUser(User user);
	
	public User authenticateUser(String userId, String password);
	
	public List<GroupMemberInfoDTO> getMatchingUserID(String str);
	
	public User getUserByUserId(String userId);
	
	public User getUserWithSecurityQuestion(String userId);
	
	public User getUser(long id);
	
	public List<GroupMemberInfoDTO> getMatchingUserIDForGroup(String str, long groupID);
}
