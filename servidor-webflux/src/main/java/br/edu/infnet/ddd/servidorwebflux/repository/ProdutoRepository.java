package br.edu.infnet.ddd.servidorwebflux.repository;

import br.edu.infnet.ddd.servidorwebflux.model.Produto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProdutoRepository extends ReactiveCrudRepository<Produto, Long> {
}
