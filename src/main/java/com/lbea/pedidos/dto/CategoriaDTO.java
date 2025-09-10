package com.lbea.pedidos.dto;

import com.lbea.pedidos.entities.Categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoriaDTO {
	
	private Long id;
	
	@NotBlank(message = "Campo obrigatório")
	@Size(min = 3, max = 80, message = "deve ter entre 3 e 60 caracteres")
	private String nome;
	
	public CategoriaDTO(Categoria entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
	}

}
