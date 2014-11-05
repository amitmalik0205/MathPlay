package com.qait.mathplay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qait.mathplay.dao.IGroupDao;
import com.qait.mathplay.dao.domain.Group;
import com.qait.mathplay.dao.domain.User;
import com.qait.mathplay.dto.GroupDTO;

@Service("groupService")
public class GroupServiceImpl implements IGroupService {

	@Autowired
	private IGroupDao groupDao;

	@Override
	public void saveGroup(Group group) {
		groupDao.create(group);
	}

	@Override
	public Group getGroupByGroupName(String groupName) {
		return groupDao.getGroupByGroupName(groupName);
	}

	@Override
	public List<Group> getGroupListForOwner(String ownerID) {
		return groupDao.getGroupListForOwner(ownerID);
	}

	@Override
	public Group getGroupByGroupId(long groupID) {
		return groupDao.getGroupByGroupId(groupID);
	}
	
	@Override
	public void deleteGroup(Group group) {
		groupDao.delete(group);
	}
	
	@Override
	public User getGroupOwner(Long groupID) {
		return groupDao.getGroupOwner(groupID);
	}

	@Override
	public List<GroupDTO> getGroupListForMember(String memberID) {
		return groupDao.getGroupListForMember(memberID);
	}
	
	@Override
	public List<GroupDTO> getGroupListForMemberAndAdmin(String memberID) {
		return groupDao.getGroupListForMemberAndAdmin(memberID);
	}
}
