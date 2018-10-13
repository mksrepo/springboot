package com.cts.healthcare.integration.service;

import org.springframework.stereotype.Service;

import com.cts.healthcare.integration.domain.ClaimHeader;

@Service("HeaderService")
public class ClaimHeaderServiceImpl implements ClaimService {
	@Override
	public String getInfo() {
		return "Claim Header Service.";
	}

	@Override
	public ClaimHeader getClaim() {
		ClaimHeader header = new ClaimHeader();
		header.setClaimNumber(1);
		header.setClaimType("Type-1");
		header.setTotalChargeAmt(10000.00);
		return header;
	}
}
