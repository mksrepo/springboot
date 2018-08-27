package com.cts.healthcare.integration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cts.healthcare.integration.service.ClaimHeaderService;

@Controller
@RequestMapping("/")
public class ClaimHeaderRestController {

	public static final Logger logger = LoggerFactory.getLogger(ClaimHeaderRestController.class);

	@Autowired
	private ClaimHeaderService claimHeaderService;

	@RequestMapping("/")
	public ResponseEntity<String> greeting() {
		return new ResponseEntity<String>(claimHeaderService.getInfo(), HttpStatus.OK);
	}

}