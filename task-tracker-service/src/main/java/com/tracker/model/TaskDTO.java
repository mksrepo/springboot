package com.tracker.model;

public class TaskDTO {
	private String taskCode;
	private String title;
	private String owner;
	private String status;
	private String complexity;
	private Double detailEstimateHrs;
	private Double doneHrs;
	private Double effortHrs;
	private Double toDoHrs;

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComplexity() {
		return complexity;
	}

	public void setComplexity(String complexity) {
		this.complexity = complexity;
	}

	public Double getDetailEstimateHrs() {
		return detailEstimateHrs;
	}

	public void setDetailEstimateHrs(Double detailEstimateHrs) {
		this.detailEstimateHrs = detailEstimateHrs;
	}

	public Double getDoneHrs() {
		return doneHrs;
	}

	public void setDoneHrs(Double doneHrs) {
		this.doneHrs = doneHrs;
	}

	public Double getEffortHrs() {
		return effortHrs;
	}

	public void setEffortHrs(Double effortHrs) {
		this.effortHrs = effortHrs;
	}

	public Double getToDoHrs() {
		return toDoHrs;
	}

	public void setToDoHrs(Double toDoHrs) {
		this.toDoHrs = toDoHrs;
	}

}
