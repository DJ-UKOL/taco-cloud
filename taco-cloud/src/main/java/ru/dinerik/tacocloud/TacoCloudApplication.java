package ru.dinerik.tacocloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Основной класс который запускает проект
@SpringBootApplication			// (@SpringBootConfiguration, @EnableAutoConfiguration, @ComponentScan)
public class TacoCloudApplication {

	public static void main(String[] args) {
		System.out.println(1 + '0');
		SpringApplication.run(TacoCloudApplication.class, args);
	}
}