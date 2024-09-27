package br.edu.infnet.micro_servicos_at.client;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ServiceClient {

    private final RestTemplate restTemplate;

    public ServiceClient(@LoadBalanced RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getStatus() {
        return restTemplate.getForObject("http://MICRO_SERVICOS_AT/status", String.class);
    }
}
