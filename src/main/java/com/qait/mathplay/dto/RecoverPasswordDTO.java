package com.qait.mathplay.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


public class RecoverPasswordDTO implements Serializable {

	private static final long serialVersionUID = -9128445344552506847L;

	@NotNull(message = "{RecoverPasswordDTO.userid.empty}")
	@Length(min = 1, message = "{RecoverPasswordDTO.userid.empty}")
	private String userID;
	
	@NotNull(message= "{RecoverPasswordDTO.answer.empty}")
	@Length(min = 1, message= "{RecoverPasswordDTO.answer.empty}")
	private String answer;
	
	@NotNull(message = "{RecoverPasswordDTO.question.empty}")
	private Long questionID;
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Long getQuestionID() {
		return questionID;
	}

	public void setQuestionID(Long questionID) {
		this.questionID = questionID;
	}
}
