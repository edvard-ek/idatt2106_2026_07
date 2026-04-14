package edu.ntnu.idi.idatt.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "edu.ntnu.idi.idatt")
@EntityScan(basePackages = "edu.ntnu.idi.idatt")
@EnableJpaRepositories(basePackages = "edu.ntnu.idi.idatt")
public class GameApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);
	}

}
