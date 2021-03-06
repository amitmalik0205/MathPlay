package com.qait.mathplay.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.qait.mathlay.enums.MemberStatus;
import com.qait.mathplay.dao.domain.Group;
import com.qait.mathplay.dao.domain.User;
import com.qait.mathplay.dto.GroupDTO;

@Repository("groupDao")
public class GroupDaoImpl extends GenericDaoImpl<Group> implements IGroupDao {
	
	public GroupDaoImpl() {
		super(Group.class);
	}
	
	@Override
	public Group getGroupByGroupName(String groupName) {
		Session session = getCurrentSession();
		Group group = null;
		String queryString = "from Group where groupName = :gName";
		Query query = session.createQuery(queryString);
		query.setString("gName", groupName);
		group = (Group) query.uniqueResult();
		return group;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Group> getGroupListForOwner(String ownerID) {
		Session session = getCurrentSession();
		List<Group> list = new ArrayList<Group>();
		String queryString = "from Group g join fetch g.groupOwner u where u.userID = :owner";
		Query query = session.createQuery(queryString);
		query.setString("owner", ownerID);
		list = query.list();
		return list;
	}
	
	@Override
	public Group getGroupByGroupId(long groupID) {
		Session session = getCurrentSession();
		Group group = null;
		group = (Group) session.get(Group.class, new Long(groupID));
		return group;
	}
	
	@Override
	public User getGroupOwner(Long groupID) {
		Session session = getCurrentSession();
		User user = null;
		String queryString = "from User u join fetch u.groups g where g.groupID = :gid";
		Query query = session.createQuery(queryString);
		query.setParameter("gid", groupID);
		user = (User)query.uniqueResult();
		return user;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<GroupDTO> getGroupListForMember(String memberID) {
		Session session = getCurrentSession();
		List<GroupDTO> list = new ArrayList<GroupDTO>();
		String queryString = "Select new com.qait.mathplay.dto.GroupDTO(g.groupID, g.groupName, u.userID, u.name, u.city, u.country) "
				+ " from GroupMember gm join gm.group g join gm.member m join g.groupOwner u where u.userID <> :memberID "
				+ " and m.userID = :memberID and gm.status <> :status";
		Query query = session.createQuery(queryString);
		query.setString("memberID", memberID);
		query.setParameter("status", MemberStatus.WAITING);
		list = query.list();
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<GroupDTO> getGroupListForMemberAndAdmin(String memberID) {
		Session session = getCurrentSession();
		List<GroupDTO> list = new ArrayList<GroupDTO>();
		String queryString = "Select new com.qait.mathplay.dto.GroupDTO(g.groupID, g.groupName, u.userID, u.name, u.city, u.country) "
				+ " from GroupMember gm join gm.group g join gm.member m join g.groupOwner u where m.userID = :memberID "
				+ " and gm.status <> :status";
		Query query = session.createQuery(queryString);
		query.setString("memberID", memberID);
		query.setParameter("status", MemberStatus.WAITING);
		list = query.list();
		return list;
	}
}
