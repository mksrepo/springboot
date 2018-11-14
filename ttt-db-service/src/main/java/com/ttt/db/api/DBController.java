package com.ttt.db.api;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ttt.db.entity.Task;
import com.ttt.db.model.TaskDTO;
import com.ttt.db.repo.TaskRepository;
import com.ttt.db.util.TaskUtil;

@RestController
@RequestMapping("/db")
public class DBController {

	@Autowired
	private TaskRepository taskRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String addTask(@RequestBody TaskDTO taskDTO) throws Exception {
		Task task = TaskUtil.convertEntityToDTO(modelMapper, taskDTO);
		Boolean notEmptyFlag = taskDTO != null && taskDTO.getTaskCode() != null && taskDTO.getTaskCode().length() > 0;
		if (!notEmptyFlag) {
			task.setDoneHrs(0);
			task.setToDoHrs(task.getDetailEstimateHrs());
			task.setTaskCode("TASK" + new Random().nextInt(100));
		} else if (task.getEffortHrs() != null && task.getEffortHrs() <= task.getToDoHrs()) {
			task.setDoneHrs(task.getDoneHrs() + task.getEffortHrs());
			task.setToDoHrs(task.getToDoHrs() - task.getEffortHrs());
		}
		taskRepo.save(task);
		StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("The Task [").append(task.getTaskCode()).append("] ").append("has been Saved by ")
				.append(task.getOwner()).append("\n").append("send by TTT Service.");
		return messageBuilder.toString();
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Task> downloadFile() throws IOException, URISyntaxException {
		return taskRepo.findAll();
	}

	// delete
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public ResponseEntity<String> deleteByById(@PathVariable("id") Integer id) {
		String msg;
		HttpStatus status = HttpStatus.OK;
		if (id == null) {
			status = HttpStatus.BAD_REQUEST;
			msg = "Record not deleted due to invalid task ID";
		} else {
			taskRepo.deleteById(id);
			msg = "This task has been deleted";
		}
		return new ResponseEntity<String>(msg, status);
	}
}
