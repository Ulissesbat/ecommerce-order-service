package com.lbea.pedidos.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_IntemPedido")
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "produto_id")
	private Produto produto;
	private int quantidade;
	private BigDecimal preco;
	
	@ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

	public BigDecimal calcularSubTotal() {
		if (produto != null && preco != null) {
			return preco.multiply(BigDecimal.valueOf(quantidade));
		}
		return BigDecimal.ZERO;
	}

}
