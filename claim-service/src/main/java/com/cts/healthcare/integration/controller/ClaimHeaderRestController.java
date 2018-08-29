package com.cts.healthcare.integration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cts.healthcare.integration.domain.ClaimHeader;
import com.cts.healthcare.integration.service.ClaimService;

@Controller
@RequestMapping("/")
public class ClaimHeaderRestController {

	public static final Logger logger = LoggerFactory.getLogger(ClaimHeaderRestController.class);

	@Autowired
	@Qualifier("HeaderService")
	private ClaimService headerService;

	@RequestMapping("/serviceinfo")
	public ResponseEntity<String> getInfo() {
		return new ResponseEntity<String>(headerService.getInfo(), HttpStatus.OK);
	}

	@RequestMapping("/claimservice")
	public ResponseEntity<ClaimHeader> getClaim() {
		return new ResponseEntity<ClaimHeader>(headerService.getClaim(), HttpStatus.OK);
	}
}