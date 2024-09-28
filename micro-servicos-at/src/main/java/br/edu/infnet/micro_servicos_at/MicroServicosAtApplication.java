package br.edu.infnet.micro_servicos_at;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroServicosAtApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServicosAtApplication.class, args);
    }

}
