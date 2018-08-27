package com.sakx.developer.demo.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cloud")
public class TestCloudConfig {

    /**
     * Add beans for running tests only in the `cloud` profile.
     * If you don't know, or not sure, please the class empty
     */



}
