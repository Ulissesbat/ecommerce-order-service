package com.lbea.pedidos.dto;

import java.math.BigDecimal;

import com.lbea.pedidos.entities.ItemPedido;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemPedidoDTO {
    private Long id;
    private Integer quantidade;
    private BigDecimal preco;
    private ProdutoDTO produto;

    public ItemPedidoDTO(ItemPedido entity) {
        this.id = entity.getId();
        this.quantidade = entity.getQuantidade();
        this.preco = entity.getPreco();
        this.produto = new ProdutoDTO(entity.getProduto());
    }
}
