package com.qait.mathplay.service;

import java.util.List;

import com.qait.mathplay.dao.domain.Group;
import com.qait.mathplay.dao.domain.User;
import com.qait.mathplay.dto.GroupDTO;

public interface IGroupService {

	public boolean saveGroup(Group group);
	
	public Group getGroupByGroupName(String groupName);
	
	public List<Group> getGroupListForOwner(String ownerID);
	
	public Group getGroupByGroupId(long groupID);
	
	public boolean delete(Group group);
	
	public User getGroupOwner(Long groupID);
	
	public List<GroupDTO> getGroupListForMember(String memberID);
}
