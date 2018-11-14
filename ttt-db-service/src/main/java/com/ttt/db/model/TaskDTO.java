package com.ttt.db.model;

public class TaskDTO {
	private Integer id;
	private String taskCode;
	private String title;
	private String owner;
	private String status;
	private String complexity;
	private Integer detailEstimateHrs;
	private Integer doneHrs;
	private Integer effortHrs;
	private Integer toDoHrs;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Integer getDetailEstimateHrs() {
		return detailEstimateHrs;
	}

	public void setDetailEstimateHrs(Integer detailEstimateHrs) {
		this.detailEstimateHrs = detailEstimateHrs;
	}

	public Integer getDoneHrs() {
		return doneHrs;
	}

	public void setDoneHrs(Integer doneHrs) {
		this.doneHrs = doneHrs;
	}

	public Integer getEffortHrs() {
		return effortHrs;
	}

	public void setEffortHrs(Integer effortHrs) {
		this.effortHrs = effortHrs;
	}

	public Integer getToDoHrs() {
		return toDoHrs;
	}

	public void setToDoHrs(Integer toDoHrs) {
		this.toDoHrs = toDoHrs;
	}

}
