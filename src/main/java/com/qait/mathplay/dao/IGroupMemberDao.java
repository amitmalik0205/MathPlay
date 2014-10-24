package com.qait.mathplay.dao;

import java.util.List;

import com.qait.mathplay.dao.domain.GroupMember;
import com.qait.mathplay.dto.GetInvitationsDTO;

public interface IGroupMemberDao extends IGenericDao<GroupMember> {
	
	public List<Object[]> getMembersInfoByGroup(long groupID);
	
	public GroupMember getGroupMemberByID(long groupID, long memberID);
	
	public void deleteGroupMember(long groupID, long memberID);
	
	public List<GetInvitationsDTO> getGroupInvitationsForUser(String userID);
	
	public Long getInvitationCount(String userID);
}
