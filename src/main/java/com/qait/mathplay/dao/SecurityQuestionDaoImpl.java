package com.qait.mathplay.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.qait.mathplay.dao.domain.SecurityQuestion;

@Repository("securityQuestionDao")
public class SecurityQuestionDaoImpl extends GenericDaoImpl<SecurityQuestion> implements ISecurityQuestionDao {

	private static final Logger logger = Logger
			.getLogger(SecurityQuestionDaoImpl.class);

	public SecurityQuestionDaoImpl() {
		super(SecurityQuestion.class);
	}
	
/*	@Override
	public List<SecurityQuestion> getAllQuestions() {
		List<SecurityQuestion> list = new ArrayList<SecurityQuestion>();
		Session session = getCurrentSession();
		String queryString = "from SecurityQuestion";
		Query query = session.createQuery(queryString);
		list = query.list();
		return list;
	}*/

	@Override
	public SecurityQuestion getQuestionById(Long questionId) {
		SecurityQuestion question = null;
		Session session = getCurrentSession();
		question = (SecurityQuestion) session.load(SecurityQuestion.class,
				questionId);
		return question;
	}
}
