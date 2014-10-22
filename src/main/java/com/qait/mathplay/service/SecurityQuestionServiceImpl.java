package com.qait.mathplay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qait.mathplay.dao.ISecurityQuestionDao;
import com.qait.mathplay.dao.domain.SecurityQuestion;

@Service("securityQuestionService")
public class SecurityQuestionServiceImpl implements ISecurityQuestionService {
	
	@Autowired
	private ISecurityQuestionDao questionDao;

	public List<SecurityQuestion> getAllQuestions() {
		return questionDao.findAll();
	}
	
	public SecurityQuestion getQuestionById(Long questionId) {
		return questionDao.getQuestionById(questionId);
	}
}
