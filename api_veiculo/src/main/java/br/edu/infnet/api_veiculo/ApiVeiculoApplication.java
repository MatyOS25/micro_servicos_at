package br.edu.infnet.api_veiculo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
@EnableDiscoveryClient
public class ApiVeiculoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiVeiculoApplication.class, args);
	}

}
