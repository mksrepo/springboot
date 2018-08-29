package com.groovy.sql.runtime.service;

public interface ScriptService {
	public Object call(String scriptName, Long id) throws Exception;
}
