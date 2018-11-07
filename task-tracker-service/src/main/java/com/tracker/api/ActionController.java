package com.tracker.api;

import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.entity.Task;
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
	public String addTask(@RequestBody TaskDTO taskDTO) {
		Boolean notEmptyFlag = taskDTO != null && taskDTO.getTaskCode() != null && taskDTO.getTaskCode().length() > 0;
		if (notEmptyFlag) {
			//Task task = TaskUtil.convertToVo(modelMapper, taskDTO);
			Task task = taskRepo.getOne(1);
			task.setTitle("lll");
			task.setId(1);
			taskRepo.save(task);
		} else {
			taskDTO.setTaskCode("TASK" + new Random().nextInt(50));
			taskRepo.save(TaskUtil.convertToVo(modelMapper, taskDTO));
		}

		return taskDTO.getTitle() + " shabeen saved successfully!";
	}
}
