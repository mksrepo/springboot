package com.tracker.slack;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tracker.model.Message;

@Service
public class SlackService {
	
	private final String URI = "https://hooks.slack.com/services/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";
	//private final String URI = "https://hooks.slack.com/services/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";

	public void sendMessage(Message message) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(URI, message, String.class);
	}
}
