package com.lbea.pedidos.dto;

import com.lbea.pedidos.entities.EnderecoEntrega;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnderecoEntregaDTO { 
	
	private Long id;
	
    @NotBlank(message = "A rua é obrigatória")
    @Size(min = 3, max = 100, message = "A rua deve ter entre 3 e 100 caracteres")
	private String rua;
    
    @NotBlank(message = "A cidade é obrigatória")
    @Size(min = 2, max = 60, message = "A cidade deve ter entre 2 e 60 caracteres")
	private String cidade;
    
    @NotBlank(message = "O CEP é obrigatório")
    @Pattern(
        regexp = "\\d{5}-?\\d{3}", 
        message = "O CEP deve estar no formato 00000-000 ou 00000000"
    )
	private String cep;
    
    @Min(value = 1, message = "O número deve ser maior que 0")
	private int numero;
    
	private Long pedidoId;

	public EnderecoEntregaDTO(EnderecoEntrega entity) {
		this.id = entity.getId();
		this.rua = entity.getRua();
		this.cidade = entity.getCidade();
		this.cep = entity.getCep();
		this.numero = entity.getNumero();
		this.pedidoId = entity.getPedido().getId();
		
	
	}
}
