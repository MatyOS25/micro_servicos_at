package br.edu.infnet.ddd.clientewebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ClienteWebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienteWebfluxApplication.class, args);
	}

}
