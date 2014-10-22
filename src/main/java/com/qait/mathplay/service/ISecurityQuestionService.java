package com.qait.mathplay.service;

import java.util.List;

import com.qait.mathplay.dao.domain.SecurityQuestion;

public interface ISecurityQuestionService {

	public List<SecurityQuestion> getAllQuestions();
	
	public SecurityQuestion getQuestionById(Long questionId);
}
