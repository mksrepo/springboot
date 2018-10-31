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
	private Double productiveCode;
	private Date swipeIn;
	private Date swipeOut;
	private Double spendMunite;

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

	public Double getProductiveCode() {
		return productiveCode;
	}

	public void setProductiveCode(Double productiveCode) {
		this.productiveCode = productiveCode;
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

	public Double getSpendMunite() {
		return spendMunite;
	}

	public void setSpendMunite(Double spendMunite) {
		this.spendMunite = spendMunite;
	}

}
