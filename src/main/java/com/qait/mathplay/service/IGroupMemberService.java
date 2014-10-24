package com.qait.mathplay.service;

import java.util.List;

import com.qait.mathplay.dao.domain.GroupMember;
import com.qait.mathplay.dto.GetInvitationsDTO;
import com.qait.mathplay.dto.GroupMemberInfoDTO;

public interface IGroupMemberService {

	public void saveMember(GroupMember member);
	
	public List<GroupMemberInfoDTO> getMembersInfoByGroup(long groupID);
	
	public GroupMember getGroupMemberByID(long groupID, long memberID);
	
	public void deleteGroupMember(long groupID, long memberID);
	
	public List<GetInvitationsDTO> getGroupInvitationsForUser(String userID);
	
	public Long getInvitationCount(String userID);
	
	public void saveOrUpdate(GroupMember member);
}
