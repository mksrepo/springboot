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

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Builder
@SuppressWarnings("unused")
public class ClaimHeader {
	private Long claimNumber;
	private String claimType;
	private Double totalChargeAmt;
	private Date statementFromDate;
	private Date statementToDate;
}
