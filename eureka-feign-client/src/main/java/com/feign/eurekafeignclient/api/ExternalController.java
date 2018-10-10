package com.feign.eurekafeignclient.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external-call")
@EnableEurekaClient
@EnableFeignClients
public class ExternalController {

	@Autowired
	ProxyEurekaClient proxyEurekaClient;
	@Autowired
	ProxyEurekaClient0 proxyEurekaClient0;

	@GetMapping("/hello")
	public String hello() {
		return proxyEurekaClient.greeting() + " <==> " + proxyEurekaClient0.greeting();
	}
}
