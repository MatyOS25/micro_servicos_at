package br.edu.infnet.api_veiculo.repository;

import br.edu.infnet.api_veiculo.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
