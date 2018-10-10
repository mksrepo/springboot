package com.netflix.eurekaclient.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eureka-client")
public class InternalController {

	@GetMapping("/greeting")
	public String greeting() {
		return "Greeting form Eureka Client 0!";
	}
}
