package com.tracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private String title;
	private String owner;
	private String status;
	private Double estimatePoints;
	private Double detailEstimateHrs;
	private Double doneHrs;
	private Double toDoHrs;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Double getEstimatePoints() {
		return estimatePoints;
	}

	public void setEstimatePoints(Double estimatePoints) {
		this.estimatePoints = estimatePoints;
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

	public Double getToDoHrs() {
		return toDoHrs;
	}

	public void setToDoHrs(Double toDoHrs) {
		this.toDoHrs = toDoHrs;
	}

}
