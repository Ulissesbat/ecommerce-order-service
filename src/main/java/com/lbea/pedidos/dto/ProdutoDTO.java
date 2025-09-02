package com.lbea.pedidos.dto;

import java.math.BigDecimal;

import com.lbea.pedidos.entities.Produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProdutoDTO {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private int estoque;

    public ProdutoDTO(Produto entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.preco = entity.getPreco();
        this.estoque = entity.getEstoque();
    }
}
