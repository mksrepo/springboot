package com.tracker.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.entity.Task;
import com.tracker.model.TaskDTO;
import com.tracker.repo.TaskRepository;
import com.tracker.slack.SlackService;
import com.tracker.util.TaskUtil;

@RestController
@RequestMapping("/tracker")
public class ActionController {

	@Autowired
	SlackService slackService;

	@Autowired
	private TaskRepository taskRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@RequestMapping(value = "/task/save", method = RequestMethod.POST)
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
		messageBuilder.append("The Task [").append(task.getTaskCode()).append("] ").append("has been Saved by ").append(task.getOwner())
			.append("\n")
			.append("send by TTT Service.");
		//slackService.sendMessage(new Message(messageBuilder.toString()));
		return messageBuilder.toString();
	}

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public ResponseEntity<Object> downloadFile() throws IOException, URISyntaxException {
		String filename = "csvdata.csv";
		FileWriter filewriter = null;
		try {
			List<Task> tasks = taskRepo.findAll();
			StringBuilder filecontent = new StringBuilder("ID, Title, Code, Owner, Status, Complexity, Estimate Hrs., Done Hrs., To Do Hrs.\n");
			for (Task task : tasks)
				filecontent.append(task.getId()).append(",")
				.append(task.getTitle()).append(",")
				.append(task.getTaskCode()).append(",")
				.append(task.getOwner()).append(",")
				.append(task.getStatus()).append(",")
				.append(task.getComplexity()).append(",")
				.append(task.getDetailEstimateHrs()).append(",")
				.append(task.getDoneHrs()).append(",")
				.append(task.getToDoHrs()).append(",")
				.append("\n");
			filewriter = new FileWriter(filename);
			filewriter.write(filecontent.toString());
			filewriter.flush();

			File file = new File(filename);
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
			headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
			headers.add("Pragma", "no-cache");
			headers.add("Expires", "0");

			ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length())
					.contentType(MediaType.parseMediaType("application/txt")).body(resource);
			return responseEntity;
		} catch (Exception e) {
			return new ResponseEntity<>("error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			if (filewriter != null)
				filewriter.close();
		}
	}
	
	
	// delete
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public ResponseEntity<String> getArticleById(@PathVariable("id") Integer id) {
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