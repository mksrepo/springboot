package com.cts.healthcare.integration.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class ClaimHeaderController {

	private RestTemplate restTemplate = new RestTemplate();
	private String URL;

	ClaimHeaderController(@Value("${claim.header.service.endpoint.serviceinfo}") String url) {
		this.URL = url;
	}

	@RequestMapping(value = "/headerinfo", method = RequestMethod.GET)
	public String getServiceInfo() {
		return restTemplate.getForObject(URL, String.class);
	}

}
