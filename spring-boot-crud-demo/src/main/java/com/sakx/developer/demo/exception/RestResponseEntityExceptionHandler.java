package com.sakx.developer.demo.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sakx.developer.demo.config.MessagesConfig;
import com.sakx.developer.demo.controller.ArticleRestController;
import com.sakx.developer.demo.controller.RequestBodyUtils;

@ControllerAdvice(assignableTypes = ArticleRestController.class)
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	public static final Logger logger = LoggerFactory.getLogger(ArticleRestController.class);

	public static final String GENERIC = "generic";

	@Autowired
	private MessagesConfig messageProp;

	@ExceptionHandler(value = { ArticleNotFoundException.class })
	@ResponseBody
	public ErrorInfo entityNotFound(HttpServletRequest req, HttpServletResponse response, ArticleNotFoundException ex) {
		logger.info("Received error", ex);
		response.setStatus(HttpStatus.NOT_FOUND.value());
		return new ErrorInfo(ex.getCode(), req.getRequestURL().toString(), messageProp.message(ex.getCode()),
				RequestBodyUtils.getRequestBody());
	}

	@ExceptionHandler(value = { ArticleAlreadyExistsException.class })
	@ResponseBody
	public ErrorInfo entityAlreadyExists(HttpServletRequest req, HttpServletResponse response,
			ArticleAlreadyExistsException ex) {
		logger.info("Received error", ex);
		response.setStatus(HttpStatus.CONFLICT.value());
		return new ErrorInfo(ex.getCode(), req.getRequestURL().toString(), messageProp.message(ex.getCode()),
				RequestBodyUtils.getRequestBody());
	}

	@ExceptionHandler(value = { BadRequestException.class })
	@ResponseBody
	public ErrorInfo badRequest(HttpServletRequest req, BadRequestException ex) {
		logger.info("Received error", ex);
		return new ErrorInfo(ex.getCode(), req.getRequestURL().toString(), messageProp.message(ex.getCode()),
				RequestBodyUtils.getRequestBody());
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public ErrorInfo badRequest(HttpServletRequest req, Exception ex) {
		logger.info("Received error", ex);
		return new ErrorInfo(GENERIC, req.getRequestURL().toString(), messageProp.message(GENERIC),
				RequestBodyUtils.getRequestBody());
	}

}
