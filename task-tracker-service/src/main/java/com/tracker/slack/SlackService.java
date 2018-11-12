package com.tracker.slack;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URISyntaxException;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tracker.model.Message;
import com.tracker.util.ProxyUtil;

@Service
public class SlackService {

	private final String URI = "https://hooks.slack.com/services/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";
	// private final String URI = "https://hooks.slack.com/services/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";

	public void sendMessage(Message message) throws URISyntaxException {
		RestTemplate restTemplate;
		ProxyUtil proxyUtil = new ProxyUtil();
		if (proxyUtil.getHostName() != null) {
			SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyUtil.getHostName(), proxyUtil.getHostPort()));
			clientHttpRequestFactory.setProxy(proxy);
			restTemplate = new RestTemplate(clientHttpRequestFactory);
		} else {
			restTemplate = new RestTemplate();
		}
		restTemplate.postForObject(URI, message, String.class);
	}
}
