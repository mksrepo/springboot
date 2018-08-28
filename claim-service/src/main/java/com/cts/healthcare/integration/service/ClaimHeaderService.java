package com.cts.healthcare.integration.service;

import org.springframework.stereotype.Service;

import com.cts.healthcare.integration.domain.ClaimHeader;

@Service("HeaderServic")
public class ClaimHeaderService implements ClaimService {
	@Override
	public String getInfo() {
		return "Claim Header Service.";
	}

	@Override
	public ClaimHeader getHeaderById(Integer id) {
		ClaimHeader header = new ClaimHeader();
		header.setClaimNumber(id);
		header.setClaimType("Type1");
		return header;
	}
}
