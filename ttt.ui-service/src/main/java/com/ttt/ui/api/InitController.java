package com.ttt.ui.api;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class InitController {
	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/")
	public String showTracker(Map<String, Object> model)
			throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException {
		model.put("ALL_TASK", restTemplate.getForObject("https://ttt-db-service.cfapps.io/db/all", List.class));
		return "tracker";
	}

}
