package ch.ozan.omar.m295.boxingresfoya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BoxingresfoyaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoxingresfoyaApplication.class, args);
	}

}
