package com.grupocmc.protein.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
@ConditionalOnExpression("${endpoint.aspect.enabled:true}")
public class LoggableAspect {
	private static final Logger LOG = LoggerFactory.getLogger(LoggableAspect.class);

	@Before("within(com.grupocmc.protein.controller..*)")
	public void endpointBefore(JoinPoint p) {
		//TODO - Meter información en el log del usuario que está accediendo
		LOG.debug(p.getTarget().getClass().getSimpleName() + " " + p.getSignature().getName() + " START");
		Object[] signatureArgs = p.getArgs();
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String request = "";
			for (int i = 0; i < signatureArgs.length; i++) {
				request = request + "PARAM" + (i+1) + "=" + mapper.writeValueAsString(signatureArgs[i]) + " ";
			}
			LOG.debug("\nRequest: \n" + request);
		} catch (JsonProcessingException e) {
			LOG.error(e.getMessage());
		}
	}

	@AfterReturning(value = ("within(com.grupocmc.protein.controller..*)"), returning = "returnValue")
	public void endpointAfterReturning(JoinPoint p, Object returnValue) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			LOG.debug("\nResponse: \n" + mapper.writeValueAsString(returnValue));
		} catch (JsonProcessingException e) {
			LOG.error(e.getMessage());
		}
		LOG.debug(p.getTarget().getClass().getSimpleName() + " " + p.getSignature().getName() + " END");
	}

	@AfterThrowing(pointcut = ("within(com.grupocmc.protein.controller..*)"), throwing = "e")
	public void endpointAfterThrowing(JoinPoint p, Exception e) throws Exception {
		LOG.error(e.getMessage());
		LOG.error(p.getTarget().getClass().getSimpleName() + " " + p.getSignature().getName() + " " + e.getMessage());
	}
}