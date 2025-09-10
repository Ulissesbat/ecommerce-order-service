package com.lbea.pedidos.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.lbea.pedidos.entities.Pedido;
import com.lbea.pedidos.enums.PedidoStatus;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoDTO {

	private Long id;
	

    @PastOrPresent(message = "A data do pedido não pode ser no futuro")
	private LocalDateTime data;

	@NotNull(message = "O status do pedido é obrigatório")
	private PedidoStatus status;

	@NotNull(message = "O cliente é obrigatório")
	@Valid
	private ClienteDTO cliente;

	@Valid
	private PagamentoDTO pagamento;

	@NotNull(message = "O endereço de entrega é obrigatório")
	@Valid
	private EnderecoEntregaDTO enderecoEntrega;

	@NotEmpty(message = "O pedido deve conter pelo menos um item")
	@Valid
	private List<ItemPedidoDTO> itens;

	public PedidoDTO(Pedido entity) {
		this.id = entity.getId();
		this.data = entity.getData();
		this.status = entity.getStatus();
		this.cliente = new ClienteDTO(entity.getCliente());
		this.pagamento = entity.getPagamento() != null ? new PagamentoDTO(entity.getPagamento()) : null;
		this.enderecoEntrega = entity.getEnderecoEntrega() != null ? new EnderecoEntregaDTO(entity.getEnderecoEntrega())
				: null;
		this.itens = entity.getItens() != null
				? entity.getItens().stream().map(ItemPedidoDTO::new).collect(Collectors.toList())
				: null;
	}

}
