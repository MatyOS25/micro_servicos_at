package br.edu.infnet.api_veiculo.service;

import br.edu.infnet.api_veiculo.model.Veiculo;
import br.edu.infnet.api_veiculo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscarPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    public Veiculo adicionar(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public Optional<Veiculo> atualizar(Long id, Veiculo veiculoAtualizado) {
        return veiculoRepository.findById(id)
            .map(veiculo -> {
                veiculo.setMarca(veiculoAtualizado.getMarca());
                veiculo.setModelo(veiculoAtualizado.getModelo());
                veiculo.setAno(veiculoAtualizado.getAno());
                return veiculoRepository.save(veiculo);
            });
    }

    public boolean remover(Long id) {
        if (veiculoRepository.existsById(id)) {
            veiculoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
