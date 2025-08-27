package com.lbea.pedidos.dto;

import java.util.List;

import com.lbea.pedidos.entities.Cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ClienteDTO {

	private Long id;
	private String nome;
	private String email;
	private String cpf;

	private List<PedidoMinDTO> pedidos;
	
	 public ClienteDTO(Cliente entity) {
	        this.id = entity.getId();
	        this.nome = entity.getNome();
	        this.email = entity.getEmail();
	        this.cpf = entity.getCpf();
	        pedidos = entity.getPedidos()
                    .stream()
                    .map(PedidoMinDTO::new)
                    .toList();
	    }

}
