package com.sakx.developer.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Config class for error messages. Messages are keyed using the format
 * {@code message.error-code}.
 *
 */
@Component
@ConfigurationProperties("")
public class MessagesConfig {
	
	private final Map<String, String> message = new HashMap<>();
	
	public Map<String, String> getMessage() {
		return message;
	}

	
	public String message(String code, Object... params) {
		String message = this.message.get(code);
		if (params.length == 0) {
			return message;
		}
		return String.format(message, params);
	}
}
