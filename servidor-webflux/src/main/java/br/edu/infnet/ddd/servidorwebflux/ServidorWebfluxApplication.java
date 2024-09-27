package br.edu.infnet.ddd.servidorwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
@EnableDiscoveryClient
public class ServidorWebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServidorWebfluxApplication.class, args);
	}

}
