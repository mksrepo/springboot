package com.groovy.sql.runtime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.groovy.sql.runtime.service.ScriptService;

@RestController
@RequestMapping("/")
public class GroovyController {

	@Autowired
	@Qualifier("ScriptEngineImpl")
	ScriptService scriptService;

	@RequestMapping(value = "/groovy/{script}/{id}", method = RequestMethod.GET)
	public String getServiceInfo(@PathVariable("script") String scriptName, @PathVariable("id") Long id) {
		try {
			return (String) scriptService.call(scriptName, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Internal server error.";
	}
}
