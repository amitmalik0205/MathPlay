package com.qait.mathplay.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.qait.mathlay.enums.MemberStatus;
import com.qait.mathplay.dao.domain.GroupMember;
import com.qait.mathplay.dto.GetInvitationsDTO;

@Repository("groupMemberDao")
public class GroupMemberDaoImpl extends GenericDaoImpl<GroupMember> implements IGroupMemberDao {


	public GroupMemberDaoImpl() {
		super(GroupMember.class);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getMembersInfoByGroup(long groupID) {
		List<Object[]> list = new ArrayList<Object[]>();
		Session session = getCurrentSession();
		String queryStr = "Select u.id, u.userID, u.name, u.city, u.country, gm.status from GroupMember gm "
				+ " join gm.group g join gm.member u where g.groupID = :gid";
		Query query = session.createQuery(queryStr);
		query.setParameter("gid", groupID);
		list = query.list();
		return list;
	}

	@Override
	public GroupMember getGroupMemberByID(long groupID, long memberID) {
		Session session = getCurrentSession();
		GroupMember groupMember = null;
		String queryStr = "from GroupMember gm where gm.groupMemberID.memberID=:mid and gm.groupMemberID.groupID=:gid";
		Query query = session.createQuery(queryStr);
		query.setParameter("mid", memberID);
		query.setParameter("gid", groupID);
		groupMember = (GroupMember) query.uniqueResult();
		return groupMember;
	}

	@Override
	public void deleteGroupMember(long groupID, long memberID) {
		Session session = getCurrentSession();
		String queryStr = "Delete from GroupMember gm where gm.groupMemberID.memberID=:mid and gm.groupMemberID.groupID=:gid";
		Query query = session.createQuery(queryStr);
		query.setParameter("mid", memberID);
		query.setParameter("gid", groupID);
		query.executeUpdate();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<GetInvitationsDTO> getGroupInvitationsForUser(String userID) {
		Session session = getCurrentSession();
		List<GetInvitationsDTO> list = null;
		String queryStr = "Select new com.qait.mathplay.dto.GetInvitationsDTO(g.groupName, g.groupID, u.userID, u.name, u.city, u.country, m.id) "
				+ " from GroupMember gm join gm.group g join g.groupOwner u join gm.member m where m.userID = :uid and gm.status = :status";
		Query query = session.createQuery(queryStr);
		query.setString("uid", userID);
		query.setParameter("status", MemberStatus.WAITING);
		list = query.list();
		return list;
	}

	@Override
	public Long getInvitationCount(String userID) {
		Session session = getCurrentSession();
		Long count = null;
		String queryStr = "Select count(*) from GroupMember gm join gm.group g join g.groupOwner u join gm.member m "
				+ " where m.userID = :uid and gm.status = :status";
		Query query = session.createQuery(queryStr);
		query.setString("uid", userID);
		query.setParameter("status", MemberStatus.WAITING);
		count = (Long)query.uniqueResult();
		return count;
	}
}
