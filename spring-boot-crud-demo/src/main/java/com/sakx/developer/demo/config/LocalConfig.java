package com.sakx.developer.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
public class LocalConfig {

    /**
     * Add beans for running tests only in the `default` profile.
     * That is, running either locally or in Concourse builds as part of CI/CD pipeline.
     *
     * Some examples - if testing using mock objects, implement the beans for Mock here.
     *
     * If you don't know, or not sure, please the class empty
     */

}