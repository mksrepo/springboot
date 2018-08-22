package io.pivotal.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {

	@JsonProperty("people")
	private List<Employee> employees;

	public List<Employee> getEmployees() {
		return employees;
	}
}