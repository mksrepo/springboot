package com.tracker.api;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tracker")
public class MainController {

	private String helloMessage = "Welcome to task tracker!";

	@RequestMapping("/board")
	public String showTracker(Map<String, String> model) {
		model.put("message", helloMessage);
		return "tracker";
	}

}
