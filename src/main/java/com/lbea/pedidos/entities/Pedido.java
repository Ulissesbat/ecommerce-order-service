package com.lbea.pedidos.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.lbea.pedidos.enums.PedidoStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_pedido")
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime data;

	@Enumerated(EnumType.STRING)
	private PedidoStatus status;

	// Cada pedido pertence a um cliente
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens;
	
	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Pagamento pagamento;
	
	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
	private EnderecoEntrega enderecoEntrega;

	public BigDecimal calcularTotal() {
		return itens.stream().map(ItemPedido::calcularSubTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
