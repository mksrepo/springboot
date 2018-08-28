package com.cts.healthcare.integration.domain;

import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class ClaimHeader {
	private Integer claimNumber;
	private String claimType;
	private Double totalChargeAmt;
	private Date statementFromDate;
	private Date statementToDate;

	public Integer getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(Integer claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public Double getTotalChargeAmt() {
		return totalChargeAmt;
	}

	public void setTotalChargeAmt(Double totalChargeAmt) {
		this.totalChargeAmt = totalChargeAmt;
	}

	public Date getStatementFromDate() {
		return statementFromDate;
	}

	public void setStatementFromDate(Date statementFromDate) {
		this.statementFromDate = statementFromDate;
	}

	public Date getStatementToDate() {
		return statementToDate;
	}

	public void setStatementToDate(Date statementToDate) {
		this.statementToDate = statementToDate;
	}

}
