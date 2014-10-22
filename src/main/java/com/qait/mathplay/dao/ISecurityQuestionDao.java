package com.qait.mathplay.dao;

import com.qait.mathplay.dao.domain.SecurityQuestion;

public interface ISecurityQuestionDao extends IGenericDao<SecurityQuestion> {

	public SecurityQuestion getQuestionById(Long questionId);
}
