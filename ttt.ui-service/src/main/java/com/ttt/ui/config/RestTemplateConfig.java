package com.ttt.ui.config;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URISyntaxException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.ttt.ui.util.ProxyUtil;

@Configuration
public class RestTemplateConfig {
	
	@Bean
	public static RestTemplate getRestTemplate() throws URISyntaxException {
		RestTemplate restTemplate;
		ProxyUtil proxyUtil = new ProxyUtil();
		if (proxyUtil.getHostName() != null) {
			SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
			Proxy proxy = new Proxy(Proxy.Type.HTTP,
					new InetSocketAddress(proxyUtil.getHostName(), proxyUtil.getHostPort()));
			clientHttpRequestFactory.setProxy(proxy);
			restTemplate = new RestTemplate(clientHttpRequestFactory);
		} else {
			restTemplate = new RestTemplate();
		}
		return restTemplate;
	}
}
