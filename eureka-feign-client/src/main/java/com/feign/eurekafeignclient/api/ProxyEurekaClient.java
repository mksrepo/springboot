package com.feign.eurekafeignclient.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("spring-cloud-eureka-client")
public interface ProxyEurekaClient {
	@RequestMapping("/eureka-client/greeting")
    String greeting();
}
