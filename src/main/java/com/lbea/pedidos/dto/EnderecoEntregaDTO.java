package com.lbea.pedidos.dto;

import com.lbea.pedidos.entities.EnderecoEntrega;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EnderecoEntregaDTO {
	
	private Long id;
	private String rua;
	private String cidade;
	private String cep;
	private int numero;

	public EnderecoEntregaDTO(EnderecoEntrega entity) {
		this.id = entity.getId();
		this.rua = entity.getRua();
		this.cidade = entity.getCidade();
		this.cep = entity.getCep();
		this.numero = entity.getNumero();
	
	}
}
