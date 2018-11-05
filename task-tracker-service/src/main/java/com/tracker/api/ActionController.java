package com.tracker.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.TaskDO;

@RestController
@RequestMapping("/tracker")
public class ActionController {

	@RequestMapping(value = "/task/save", method = RequestMethod.POST)
	public String addTask(@RequestBody TaskDO task) {
		System.out.println(task.getOwner());
		return task.getTitle() + " Saved";
	}
}
