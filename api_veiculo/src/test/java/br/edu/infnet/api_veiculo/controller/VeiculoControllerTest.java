package br.edu.infnet.api_veiculo.controller;

import br.edu.infnet.api_veiculo.model.Veiculo;
import br.edu.infnet.api_veiculo.service.VeiculoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VeiculoController.class)
public class VeiculoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VeiculoService veiculoService;

    @Test
    public void testListarTodos() throws Exception {
        when(veiculoService.listarTodos()).thenReturn(Arrays.asList(
                new Veiculo(1L, "Ford", "Fiesta", 2020),
                new Veiculo(2L, "Chevrolet", "Onix", 2021)
        ));

        mockMvc.perform(get("/veiculos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].marca").value("Ford"))
                .andExpect(jsonPath("$[1].marca").value("Chevrolet"));
    }

    @Test
    public void testBuscarPorId() throws Exception {
        when(veiculoService.buscarPorId(1L)).thenReturn(Optional.of(new Veiculo(1L, "Ford", "Fiesta", 2020)));

        mockMvc.perform(get("/veiculos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca").value("Ford"));
    }

    @Test
    public void testAdicionar() throws Exception {
        Veiculo novoVeiculo = new Veiculo(null, "Honda", "Civic", 2022);
        when(veiculoService.adicionar(any(Veiculo.class))).thenReturn(new Veiculo(3L, "Honda", "Civic", 2022));

        mockMvc.perform(post("/veiculos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"marca\":\"Honda\",\"modelo\":\"Civic\",\"ano\":2022}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.marca").value("Honda"));
    }

    @Test
    public void testAtualizar() throws Exception {
        Veiculo veiculoAtualizado = new Veiculo(1L, "Ford", "Focus", 2021);
        when(veiculoService.atualizar(any(Long.class), any(Veiculo.class))).thenReturn(Optional.of(veiculoAtualizado));

        mockMvc.perform(put("/veiculos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"marca\":\"Ford\",\"modelo\":\"Focus\",\"ano\":2021}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelo").value("Focus"));
    }

    @Test
    public void testRemover() throws Exception {
        when(veiculoService.remover(1L)).thenReturn(true);

        mockMvc.perform(delete("/veiculos/1"))
                .andExpect(status().isOk());
    }
}
