package com.cts.healthcare.integration.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cts.healthcare.integration.domain.ClaimHeader;

@RestController
@RequestMapping("/")
public class ClaimHeaderController {

	private RestTemplate restTemplate = new RestTemplate();
	private String URL_SERVICEINFO;
	private String URL_HEADERINFO;

	ClaimHeaderController(@Value("${claim.header.service.endpoint.serviceinfo}") String URL_SERVICEINFO,
			@Value("${claim.header.service.endpoint.headerinfo}") String URL_HEADERINFO) {
		this.URL_SERVICEINFO = URL_SERVICEINFO;
		this.URL_HEADERINFO = URL_HEADERINFO;
	}

	@RequestMapping(value = "/headerserviceinfo", method = RequestMethod.GET)
	public String getServiceInfo() {
		return restTemplate.getForObject(URL_SERVICEINFO, String.class);
	}

	@RequestMapping(value = "/claim", method = RequestMethod.GET)
	public ClaimHeader getClaim() {
		ParameterizedTypeReference<Resource<ClaimHeader>> inHeader = new ParameterizedTypeReference<Resource<ClaimHeader>>(){};
		ResponseEntity<Resource<ClaimHeader>> response = 
				restTemplate.exchange(RequestEntity.get(URI.create(this.URL_HEADERINFO)).accept(MediaTypes.HAL_JSON).build(), inHeader);
		assert response != null;
		if (response.getStatusCode() == HttpStatus.OK) {
			ClaimHeader outHeader = response.getBody().getContent();
			assert outHeader != null;
			return outHeader;
		}
		return new ClaimHeader() ;
	}
}
