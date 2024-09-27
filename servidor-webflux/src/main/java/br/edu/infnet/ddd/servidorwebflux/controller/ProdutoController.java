package br.edu.infnet.ddd.servidorwebflux.controller;

import br.edu.infnet.ddd.servidorwebflux.model.Produto;
import br.edu.infnet.ddd.servidorwebflux.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Value("${spring.application.name}")
    private String serviceName;

    @GetMapping
    public Flux<Produto> getAllProdutos() {
        return produtoRepository.findAll()
                .map(this::addServiceInfo);
    }

    @GetMapping("/{id}")
    public Mono<Produto> getProdutoById(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(this::addServiceInfo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Produto> createProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto)
                .map(this::addServiceInfo);
    }

    private Produto addServiceInfo(Produto produto) {
        produto.setServiceInfo("Provided by: " + serviceName);
        return produto;
    }
}
