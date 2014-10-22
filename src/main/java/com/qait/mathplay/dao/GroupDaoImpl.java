package com.qait.mathplay.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.qait.mathlay.enums.MemberStatus;
import com.qait.mathplay.dao.domain.Group;
import com.qait.mathplay.dao.domain.User;
import com.qait.mathplay.dto.GroupDTO;

@Repository("groupDao")
public class GroupDaoImpl extends GenericDaoImpl<Group> implements IGroupDao {
	
	private static final Logger logger = Logger.getLogger(GroupDaoImpl.class);

	public GroupDaoImpl() {
		super(Group.class);
	}
	
	@Override
	public boolean saveGroup(Group group) {
		boolean groupSaved = true;
		Session session = getCurrentSession();
		session.save(group);
		return groupSaved;
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
	public List<Group> getGroupListForOwner(String ownerID) {
		Session session = getCurrentSession();
		List<Group> list = new ArrayList<Group>();
		String queryString = "from Group g where g.groupOwner.userID = :owner";
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
	public boolean deleteGroup(Group group) {
		boolean groupDeleted = true;
		Session session = getCurrentSession();
		session.delete(group);
		return groupDeleted;
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
	public List<GroupDTO> getGroupListForMember(String memberID) {
		Session session = getCurrentSession();
		List<GroupDTO> list = new ArrayList<GroupDTO>();
		String queryString = "Select new com.qait.mathplaynlearn.dto.GroupDTO(g.groupID, g.groupName) from GroupMember gm "
				+ " join gm.group g join gm.member m join g.groupOwner u where u.userID <> :memberID "
				+ " and m.userID = :memberID and gm.status <> :status";
		Query query = session.createQuery(queryString);
		query.setString("memberID", memberID);
		query.setParameter("status", MemberStatus.WAITING);
		list = query.list();
		return list;
	}
}
