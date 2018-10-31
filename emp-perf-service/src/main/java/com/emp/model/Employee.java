package com.emp.model;

import java.util.Date;

/**
 * @author Khusboo Singh
 * @for this class is to define all properties related to Employee
 *
 */
public class Employee {
	private String name;
	private Date date;
	private String minuteCode;
	private Date swipeIn;
	private Date swipeOut;
	private Integer minute;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMinuteCode() {
		return minuteCode;
	}

	public void setMinuteCode(String minuteCode) {
		this.minuteCode = minuteCode;
	}

	public Date getSwipeIn() {
		return swipeIn;
	}

	public void setSwipeIn(Date swipeIn) {
		this.swipeIn = swipeIn;
	}

	public Date getSwipeOut() {
		return swipeOut;
	}

	public void setSwipeOut(Date swipeOut) {
		this.swipeOut = swipeOut;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

}
