package com.qait.mathplay.rest.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.qait.mathplay.rest.MathPlayNLearnServiceResponse;
import com.qait.mathplay.util.MathPlayNLearnUtil;
import com.qait.mathplay.util.MathPlayPropertiesFileReaderUtil;

/**
 * This ExceptionMapper is used to catch DataIntegrityViolationException
 * exceptions. We are using @Transactional on the Jersey services. Any
 * SQLException occurred is converted to HibernateException by Hibernate and it
 * further converted to DataAccessException by Spring using @Repositry
 * annotation. Although we are executing the whole logic inside try-catch block
 * so that any exception can be caught. But Spring uses AOP while using
 * @Transactional. Now if there is some DataAccessException then this will not
 * be caught by the catch all Exception in the service method.The problem is @Transactional
 * makes a AOP proxy out of this service and so the transaction is not committed
 * until the method exits. So when the method is completed then transaction is
 * committed and Exception is thrown to upper layer. Since we do not have any
 * upper layer(caller) exception got uncaught. There are two ways to fix this
 * problem 
 * 1. Explicitly flush the session at DAO Layer which causes exception is thrown at DAO layers itself.
 * 2. Do not catch exception at Jersey Service method but the layer above it.
 * 
 * Since we do not have any upper layer(caller) we are using ExceptionMapper for this.
 */

@Provider
public class DataAccessExceptionMapper implements ExceptionMapper<DataAccessException> {

	private static final Logger logger = Logger
			.getLogger(DataAccessExceptionMapper.class);

	@Override
	public Response toResponse(DataAccessException ex) {
		ex.printStackTrace();
		logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(ex));
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();
		response.setCode("genericErrorMsg001");
		response.setMessage(MathPlayPropertiesFileReaderUtil
				.getPropertyValue("genericErrorMsg001"));
		return Response.ok(response).build();
	}
}