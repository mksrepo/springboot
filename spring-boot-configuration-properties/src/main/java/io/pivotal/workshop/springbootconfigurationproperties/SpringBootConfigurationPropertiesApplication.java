package io.pivotal.workshop.springbootconfigurationproperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootConfigurationPropertiesApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootConfigurationPropertiesApplication.class, args);
	}
	
	final private PersonProperties personProperties;

	@Autowired
	public SpringBootConfigurationPropertiesApplication(PersonProperties personProperties) {
		this.personProperties = personProperties;
	}

	@RequestMapping("/")
	public String greetings() {
		return personProperties.getGreeting() + " Spring Boot! " + personProperties.getFarewell() + " Spring Boot!";
	}
}
