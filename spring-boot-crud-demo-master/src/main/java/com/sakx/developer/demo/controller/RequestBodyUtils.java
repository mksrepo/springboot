package com.sakx.developer.demo.controller;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

/**
 * Utility class that stores/retrieves payloads obtained through
 * the @RequestBody annotation.
 * 
 * @author 107406
 *
 */
public class RequestBodyUtils {

	private static ApplicationContext context;

	public static void setRequestBody(Object payload) {
		context.getBean(InputHolder.class).input = payload;
	}

	public static Object getRequestBody() {
		return context.getBean(InputHolder.class).input;
	}

	/**
	 * Helper bean that registers an ApplicationContext with the
	 * RequestBodyUtils
	 * 
	 * @author 107406
	 *
	 */
	@Component
	@Scope(scopeName = "singleton")
	static class ApplicationContextHolder implements ApplicationContextAware {
		ApplicationContext context;

		@Override
		public void setApplicationContext(ApplicationContext arg0) throws BeansException {
			context = arg0;
			RequestBodyUtils.context = context;
		}
	};

	/**
	 * A holder bean that stores the @RequestBody object, for the duration of
	 * the request.
	 * 
	 * @author 107406
	 *
	 */
	@Component
	@Scope(scopeName = "request")
	public static class InputHolder {
		public Object input;
	}

	/**
	 * Advice that stores the object injected by the @RequestBody annotation.
	 * 
	 * @author 107406
	 *
	 */
	@ControllerAdvice
	static class RequestBodyStoringAdvice implements RequestBodyAdvice {
		/*
		 * These methods are whenever Spring parses a RequestBody annotation,
		 * before the controller is called.
		 */
		@Override
		public Object afterBodyRead(Object arg0, HttpInputMessage arg1, MethodParameter arg2, Type arg3,
				Class<? extends HttpMessageConverter<?>> arg4) {
			RequestBodyUtils.setRequestBody(arg0);
			return arg0;
		}

		@Override
		public HttpInputMessage beforeBodyRead(HttpInputMessage arg0, MethodParameter arg1, Type arg2,
				Class<? extends HttpMessageConverter<?>> arg3) throws IOException {
			return arg0;
		}

		@Override
		public Object handleEmptyBody(Object arg0, HttpInputMessage arg1, MethodParameter arg2, Type arg3,
				Class<? extends HttpMessageConverter<?>> arg4) {
			return arg0;
		}

		@Override
		public boolean supports(MethodParameter arg0, Type arg1, Class<? extends HttpMessageConverter<?>> arg2) {
			return true;
		}

	}

}
