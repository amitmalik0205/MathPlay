package com.qait.mathplay.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.qait.mathlay.enums.MemberStatus;
import com.qait.mathlay.enums.USER_STATUS_TYPE;
import com.qait.mathplay.dao.IGroupMemberDao;
import com.qait.mathplay.dao.domain.GroupMember;
import com.qait.mathplay.dto.GetInvitationsDTO;
import com.qait.mathplay.dto.GroupMemberInfoDTO;

@Service("groupMemberService")
public class GroupMemberServiceImpl implements IGroupMemberService {

	@Autowired
	private IGroupMemberDao groupMemberDao;
	
	@Autowired
	@Qualifier("msgConfig")
	private Properties msgConfig;

	@Override
	public void saveMember(GroupMember member) {
		groupMemberDao.create(member);
	}

	@Override
	public List<GroupMemberInfoDTO> getMembersInfoByGroup(long groupID) {
		List<GroupMemberInfoDTO> membersInfoList = new ArrayList<GroupMemberInfoDTO>();
		List<Object[]> list = groupMemberDao.getMembersInfoByGroup(groupID);

		for (Object[] objArr : list) {
			GroupMemberInfoDTO dto = new GroupMemberInfoDTO();
			dto.setUserKey((Long) objArr[0]);
			dto.setUserID((String) objArr[1]);
			dto.setName((String) objArr[2]);
			dto.setCity((String) objArr[3]);
			dto.setCountry((String) objArr[4]);
			dto.setStatus((MemberStatus) objArr[5]);			
			
			membersInfoList.add(dto);
		}
		return membersInfoList;
	}

	@Override
	public GroupMember getGroupMemberByID(long groupID, long memberID) {
		return groupMemberDao.getGroupMemberByID(groupID, memberID);
	}

	@Override
	public void deleteGroupMember(long groupID, long memberID) {
		groupMemberDao.deleteGroupMember(groupID, memberID);
	}

	@Override
	public List<GetInvitationsDTO> getGroupInvitationsForUser(String userID) {
		return groupMemberDao.getGroupInvitationsForUser(userID);
	}

	@Override
	public Long getInvitationCount(String userID) {
		return groupMemberDao.getInvitationCount(userID);
	}

	@Override
	public void saveOrUpdate(GroupMember member) {
		groupMemberDao.saveOrUpdate(member);
	}
	
	@Override
	public List<GroupMemberInfoDTO> getOnlineMembersInfoByGroup(long groupID) {
		List<GroupMemberInfoDTO> membersInfoList = new ArrayList<GroupMemberInfoDTO>();
		List<Object[]> list = groupMemberDao.getMembersInfoByGroup(groupID);
		long onlineTimeInterval = new Long(msgConfig.getProperty("online.interval"));
		
		for (Object[] objArr : list) {			
			if ((new Date().getTime()) - ((Date) objArr[6]).getTime() <= onlineTimeInterval) {				
				GroupMemberInfoDTO dto = new GroupMemberInfoDTO();
				dto.setUserKey((Long) objArr[0]);
				dto.setUserID((String) objArr[1]);
				dto.setName((String) objArr[2]);
				dto.setCity((String) objArr[3]);
				dto.setCountry((String) objArr[4]);
				dto.setStatus((MemberStatus) objArr[5]);
				dto.setUserStatus(USER_STATUS_TYPE.ONLINE);
				
				membersInfoList.add(dto);
			}							
		}
		return membersInfoList;		
	}
}
