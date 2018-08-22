package io.pivotal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.pivotal.domain.Directory;
import io.pivotal.domain.Person;
import io.pivotal.repository.DirectoryRepository;

@SpringBootApplication
public class DirectoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DirectoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner addPersons(DirectoryRepository repo) {
		return args -> {

			List<Person> people = new ArrayList<Person>() {
				private static final long serialVersionUID = -6851604214661500516L;
				{
					add(new Person("John", "Doe", "john@email.com", "Denver,CO", "1-800-9012345"));
					add(new Person("Carl", "Doe", "carl@email.com", "Albuquerque,NM", "404-2346781"));
					add(new Person("Trish", "Doe", "trish@email.com", "Miamu,FL", "305-4562789"));
				}
			};

			repo.save(new Directory("my-directory", people));

		};
	}
}