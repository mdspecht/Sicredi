package br.com.sicredi.desafio;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableMongoAuditing
@EnableRabbit
@EnableScheduling
@SpringBootApplication
public class SicrediApplication {

	public static void main(String[] args) {
		SpringApplication.run(SicrediApplication.class, args);
	}

}
