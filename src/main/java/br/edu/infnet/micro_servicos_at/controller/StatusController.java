package br.edu.infnet.micro_servicos_at.controller;

import br.edu.infnet.micro_servicos_at.client.ServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @Autowired
    private ServiceClient serviceClient;

    @GetMapping("/status")
    public String getStatus() {
        return "O serviço está ativo!";
    }

    @GetMapping("/test-load-balancing")
    public String testLoadBalancing() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            result.append(serviceClient.getStatus()).append("\n");
        }
        return result.toString();
    }
}
