package com.groovy.sql.runtime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.groovy.sql.runtime.repo.StudentRepository;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;

@Service("ScriptEngineImpl")
public class ScriptServiceImpl implements ScriptService {
	@Autowired
	StudentRepository repository;

	@Value("${groovy.script.path}")
	String groovyScriptPath;

	@Override
	public Object call(String scriptName, Long id) throws Exception {
		Binding binding = new Binding();
		binding.setVariable("id", id);
		binding.setVariable("jdbcTemplate", repository.getJdbcTemplate());
		GroovyScriptEngine engine = new GroovyScriptEngine(groovyScriptPath);
		Object ret = engine.run(scriptName + ".groovy", binding);
		System.out.println(ret);
		return ret.toString();
	}

}
