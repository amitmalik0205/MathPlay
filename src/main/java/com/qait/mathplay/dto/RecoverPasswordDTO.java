package com.qait.mathplay.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;


public class RecoverPasswordDTO implements Serializable {

	private static final long serialVersionUID = -9128445344552506847L;

	@Length(min = 1, message = "User ID can not be empty")
	@JsonProperty
	private String userID;
	
	@Length(min = 1, message = "Answer can not be empty")
	@JsonProperty
	private String answer;
	
	@Length(min = 1, message = "Question can not be empty")
	@JsonProperty
	private Long questionID;

	public List<String> validate() {
		List<String> errorMessages = new ArrayList<String>();
		Validator validator = Validation.buildDefaultValidatorFactory()
				.getValidator();

		Set<ConstraintViolation<RecoverPasswordDTO>> violations = validator
				.validate(this);
		if (!violations.isEmpty()) {
			for (ConstraintViolation<RecoverPasswordDTO> violation : violations) {
				errorMessages.add(violation.getMessage());
			}
		}
		return errorMessages;
	}
	
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
