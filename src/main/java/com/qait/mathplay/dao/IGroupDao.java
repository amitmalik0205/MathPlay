package com.qait.mathplay.dao;

import java.util.List;

import com.qait.mathplay.dao.domain.Group;
import com.qait.mathplay.dao.domain.User;
import com.qait.mathplay.dto.GroupDTO;

public interface IGroupDao extends IGenericDao<Group> {

	public Group getGroupByGroupName(String groupName);
	
	public List<Group> getGroupListForOwner(String ownerID);
	
	public Group getGroupByGroupId(long groupID);
	
	public User getGroupOwner(Long groupID);
	
	public List<GroupDTO> getGroupListForMember(String memberID);
	
	public List<GroupDTO> getGroupListForMemberAndAdmin(String memberID);
}
