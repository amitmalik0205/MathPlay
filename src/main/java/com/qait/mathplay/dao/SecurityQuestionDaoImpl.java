package com.qait.mathplay.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.qait.mathplay.dao.domain.SecurityQuestion;

@Repository("securityQuestionDao")
public class SecurityQuestionDaoImpl extends GenericDaoImpl<SecurityQuestion> implements ISecurityQuestionDao {

	public SecurityQuestionDaoImpl() {
		super(SecurityQuestion.class);
	}
	

	@Override
	public SecurityQuestion getQuestionById(Long questionId) {
		SecurityQuestion question = null;
		Session session = getCurrentSession();
		question = (SecurityQuestion) session.load(SecurityQuestion.class,
				questionId);
		return question;
	}
}
