package br.edu.infnet.ddd.servidorwebflux.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("produtos")
public class Produto {
    @Id
    private Long id;
    private String nome;
    private Double preco;
    
    @Transient
    private String serviceInfo;
}
