package com.lbea.pedidos.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.lbea.pedidos.entities.Pedido;
import com.lbea.pedidos.enums.PedidoStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PedidoDTO {

	private Long id;
	private LocalDateTime data;
	private PedidoStatus status;
	private ClienteDTO cliente;
	private PagamentoDTO pagamento;
	private EnderecoEntregaDTO enderecoEntrega;
	private List<ItemPedidoDTO> itens;
	
	 public PedidoDTO(Pedido entity) {
	        this.id = entity.getId();
	        this.data = entity.getData();
	        this.status = entity.getStatus();
	        this.cliente = new ClienteDTO(entity.getCliente());
	        this.pagamento = entity.getPagamento() != null ? new PagamentoDTO(entity.getPagamento()) : null;
	        this.enderecoEntrega = entity.getEnderecoEntrega() != null ? new EnderecoEntregaDTO(entity.getEnderecoEntrega()) : null;
	        this.itens = entity.getItens() != null 
	            ? entity.getItens().stream().map(ItemPedidoDTO::new).collect(Collectors.toList())
	            : null;
	    }

}
