package com.ttt.ui.api;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class InitController {

	@RequestMapping("/")
	public String showTracker(Map<String, Object> model) throws URISyntaxException {
		model.put("ALL_TASK", new RestTemplate().getForObject("https://ttt-db-service.cfapps.io/db/all", List.class));
		return "tracker";
	}

}
