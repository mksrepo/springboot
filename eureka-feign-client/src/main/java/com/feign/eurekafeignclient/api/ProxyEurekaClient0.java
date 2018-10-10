package com.feign.eurekafeignclient.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("spring-cloud-eureka-client-0")
public interface ProxyEurekaClient0 {
	@RequestMapping("/eureka-client/greeting")
    String greeting();
}
