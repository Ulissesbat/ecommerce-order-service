package com.lbea.pedidos.dto;

import java.math.BigDecimal;

import com.lbea.pedidos.entities.ItemPedido;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemPedidoDTO {

	@NotNull
	private Long produtoId;
	private Integer quantidade;
	private BigDecimal preco;

	public ItemPedidoDTO(ItemPedido entity) {

		this.quantidade = entity.getQuantidade();
		this.preco = entity.getPreco();
		this.produtoId = entity.getProduto().getId();

	}
}
