package com.qait.mathplay.dao;

import java.util.List;

import com.qait.mathplay.dao.domain.User;
import com.qait.mathplay.dto.GroupMemberInfoDTO;

public interface IUserDao extends IGenericDao<User> {
	
	public User getUserByEmail(String email);
	
	public User getUserByUserId(String userId);
	
	public User authenticateUser(String userId, String password);
	
	public List<GroupMemberInfoDTO> getMatchingUserID(String str);
	
	public User getUserWithSecurityQuestion(String userId);
	
	public User getUser(long id);
	
	public List<GroupMemberInfoDTO> getMatchingUserIDForGroup(String str, long groupID);
}
