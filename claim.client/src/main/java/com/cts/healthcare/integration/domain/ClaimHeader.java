package com.cts.healthcare.integration.domain;

public class ClaimHeader {
	private Integer claimNumber;
	private String claimType;
	private Double totalChargeAmt;

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

}
