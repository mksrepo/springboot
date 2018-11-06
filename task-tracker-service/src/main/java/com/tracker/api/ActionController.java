package com.tracker.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.model.TaskDTO;
import com.tracker.repo.TaskRepository;
import com.tracker.util.TaskUtil;

@RestController
@RequestMapping("/tracker")
public class ActionController {

	@Autowired
	private TaskRepository taskRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@RequestMapping(value = "/task/save", method = RequestMethod.POST)
	public String addTask(@RequestBody TaskDTO taskVo) {
		taskRepo.save(TaskUtil.convertToVo(modelMapper, taskVo));
		return taskVo.getTitle() + " shabeen saved successfully!";
	}
}
