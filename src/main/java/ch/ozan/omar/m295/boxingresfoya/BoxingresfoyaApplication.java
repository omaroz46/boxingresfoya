package ch.ozan.omar.m295.boxingresfoya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main entry point for the Boxingresfoya application.
 */
@SpringBootApplication
@EnableJpaRepositories
public class BoxingresfoyaApplication {

	/**
	 * Main method to start the application.
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(BoxingresfoyaApplication.class, args);
	}

}
