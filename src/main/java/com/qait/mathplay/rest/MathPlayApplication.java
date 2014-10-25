package com.qait.mathplay.rest;

import org.glassfish.jersey.server.ResourceConfig;

public class MathPlayApplication extends ResourceConfig {

	public MathPlayApplication() {
		// Register ExceptionMapper for validations.
		//register(ConstraintViolationExceptionMapper.class);
	}
}
