package com.qait.mathplay.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.qait.mathplay.dao.domain.User;
import com.qait.mathplay.dto.GroupMemberInfoDTO;

@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User> implements IUserDao {

	public UserDaoImpl() {
		super(User.class);
	}
	
	@Override
	public User getUserByEmail(String email) {
		Session session = getCurrentSession();
		User user = null;
		String queryString = "from User where email = :email";
		Query query = session.createQuery(queryString);
		query.setString("email", email);
		user = (User) query.uniqueResult();
		return user;
	}

	
	@Override
	public User getUserByUserId(String id) {
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userID", id));
		User user = null;
		user = (User) criteria.uniqueResult();
		return user;
	}
	
	@Override
	public User authenticateUser(String userId, String password) {
		Session session = getCurrentSession();
		User user = null;
		String queryString = "from User u where u.userID = :userId and u.password = :pwd";
		Query query = session.createQuery(queryString);
		query.setString("userId", userId);
		query.setString("pwd", password);
		user = (User) query.uniqueResult();
		return user;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<GroupMemberInfoDTO> getMatchingUserID(String str) {
		List<GroupMemberInfoDTO> list = new ArrayList<GroupMemberInfoDTO>();
		Session session = getCurrentSession();
		String queryString = "Select new com.qait.mathplay.dto.GroupMemberInfoDTO(u.id,u.userID,u.name,u.city,u.country) "
				+ " from User u where u.userID like '"+str+"%'";
		Query query = session.createQuery(queryString);
		list = query.list();
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<User> getUserWithSecurityQuestion(String userId) {
		Session session = getCurrentSession();
		String queryString = "from User u join fetch u.securityQuestion where u.userID = :userId";
		Query query = session.createQuery(queryString);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	@Override
	public User getUser(long id) {
		Session session = getCurrentSession();
		User user = null;
		user = (User) session.get(User.class, new Long(id));
		return user;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<GroupMemberInfoDTO> getMatchingUserIDForGroup(String str, long groupID) {
		List<GroupMemberInfoDTO> list = new ArrayList<GroupMemberInfoDTO>();
		Session session = getCurrentSession();
		String queryString = "Select new com.qait.mathplay.dto.GroupMemberInfoDTO(u.id,u.userID,u.name,u.city,u.country,gm.status) "
				+ " from GroupMember gm join gm.member u where gm.group.groupID=:gid and u.userID like '"+str+"%'";
		Query query = session.createQuery(queryString);
		query.setParameter("gid", groupID);
		list = query.list();
		return list;
	}
}
