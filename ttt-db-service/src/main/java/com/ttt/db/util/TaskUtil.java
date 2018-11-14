package com.ttt.db.util;

import org.modelmapper.ModelMapper;

import com.ttt.db.entity.Task;
import com.ttt.db.model.TaskDTO;

public class TaskUtil {
	public static Task convertEntityToDTO(ModelMapper modelMapper, TaskDTO taskDTO) {
		return (Task) modelMapper.map(taskDTO, Task.class);
	}

	public static TaskDTO convertDTOToEntity(ModelMapper modelMapper, Task task) {
		return (TaskDTO) modelMapper.map(task, TaskDTO.class);
	}
}
