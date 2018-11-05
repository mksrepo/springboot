package com.tracker.api;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tracker")
public class MainController {

	@RequestMapping("/board")
	public String showTracker(Map<String, String> model) {
		return "tracker";
	}

}
