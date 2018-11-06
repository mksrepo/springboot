package com.tracker.util;

import org.modelmapper.ModelMapper;

import com.tracker.entity.Task;
import com.tracker.model.TaskDTO;

public class TaskUtil {
	public static Task convertToVo(ModelMapper modelMapper, TaskDTO taskVo) {
		return (Task) modelMapper.map(taskVo, Task.class);
	}
}
