package com.tracker.api;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tracker")
public class MainController {

	private String helloMessage = "Welcome to task tracker!";

	@GetMapping("/show")
	public String showTracker(Map<String, String> model) {
		model.put("message", helloMessage);
		return "tracker";
	}

}
