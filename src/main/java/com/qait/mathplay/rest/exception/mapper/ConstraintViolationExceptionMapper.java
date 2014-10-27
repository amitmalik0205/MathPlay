package com.qait.mathplay.rest.exception.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
	
    @Override
    public Response toResponse(ConstraintViolationException exception) {
    	List<String> errorMessages = new ArrayList<String>();
    	Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
    	
    	if (!violations.isEmpty()) {
			for (ConstraintViolation<?> violation : violations) {
				errorMessages.add(violation.getMessage());
			}
		}
        return Response.ok(errorMessages).build();
    }
}
