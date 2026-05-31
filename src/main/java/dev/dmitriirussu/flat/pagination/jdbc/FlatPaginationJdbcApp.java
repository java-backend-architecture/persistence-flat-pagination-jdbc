package dev.dmitriirussu.flat.pagination.jdbc;

import dev.dmitriirussu.flat.pagination.jdbc.application.OwnerReadRepository;
import dev.dmitriirussu.flat.pagination.jdbc.application.PageRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlatPaginationJdbcApp {
	public static void main(String[] args) {
		SpringApplication.run(FlatPaginationJdbcApp.class, args);
	}
	// Demo output for manual verification of graph extraction queries
	/*@Bean
	CommandLineRunner demo(OwnerReadRepository repository) {
		return args -> {
			System.out.println("\n=== page - 0 ===\n");
			System.out.println(repository.findAllFlat(new PageRequest(0, 2)));
			System.out.println();
			System.out.println("\n=== page - 1 ===\n");
			System.out.println(repository.findAllFlat(new PageRequest(1, 2)));
			System.out.println();
		};
	}*/
}
