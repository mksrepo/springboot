package com.tracker.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tracker.repo.TaskRepository;

@Controller
@RequestMapping("/tracker")
public class SarterController {

	@Autowired
	private TaskRepository taskRepo;

	@RequestMapping("/board")
	public String showTracker(Map<String, Object> model) {
		model.put("ALL_TASK", taskRepo.findAll());
		return "tracker";
	}

}
