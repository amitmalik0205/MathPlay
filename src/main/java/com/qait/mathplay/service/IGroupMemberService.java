package com.qait.mathplay.service;

import java.util.List;

import com.qait.mathplay.dao.domain.GroupMember;
import com.qait.mathplay.dto.GetInvitationsDTO;
import com.qait.mathplay.dto.GroupMemberInfoDTO;

public interface IGroupMemberService {

	public boolean saveMember(GroupMember member);
	
	public List<GroupMemberInfoDTO> getMembersInfoByGroup(long groupID);
	
	public GroupMember getGroupMemberByID(long groupID, long memberID);
	
	public boolean deleteGroupMember(long groupID, long memberID);
	
	public List<GetInvitationsDTO> getGroupInvitationsForUser(String userID);
	
	public Long getInvitationCount(String userID);
}
