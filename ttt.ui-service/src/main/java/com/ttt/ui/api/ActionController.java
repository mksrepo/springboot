package com.ttt.ui.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ttt.ui.dto.Task;

@RestController
@RequestMapping("/tracker")
public class ActionController {

	@Autowired
	RestTemplate restTemplate;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@RequestMapping(value = "/task/save", method = RequestMethod.POST)
	public String addTask(@RequestBody Task task) throws Exception {
		return restTemplate.postForObject("https://ttt-db-service.cfapps.io/db/save", task,
				String.class);
	}

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public ResponseEntity<Object> downloadFile() throws IOException, URISyntaxException {
		String filename = "task-tracker.csv";
		FileWriter filewriter = null;
		try {
			List<Task> tasks = new ArrayList<Task>();
			ResponseEntity<List<Task>> claimResponse = restTemplate.exchange(
					"https://ttt-db-service.cfapps.io/db/all", HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Task>>() {
					});
			if (claimResponse != null && claimResponse.hasBody()) {
				tasks = claimResponse.getBody();
			}

			StringBuilder filecontent = new StringBuilder(
					"ID, Title, Code, Owner, Status, Complexity, Estimate Hrs., Done Hrs., To Do Hrs.\n");
			for (Task task : tasks)
				filecontent.append(task.getId()).append(",").append(task.getTitle()).append(",")
						.append(task.getTaskCode()).append(",").append(task.getOwner()).append(",")
						.append(task.getStatus()).append(",").append(task.getComplexity()).append(",")
						.append(task.getDetailEstimateHrs()).append(",").append(task.getDoneHrs()).append(",")
						.append(task.getToDoHrs()).append(",").append("\n");
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
	public String getArticleById(@PathVariable("id") Integer id) {
		return restTemplate.getForObject("https://ttt-db-service.cfapps.io/db/" + id + "/delete", String.class);
	}
}
