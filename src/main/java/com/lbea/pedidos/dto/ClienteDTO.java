package com.lbea.pedidos.dto;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.lbea.pedidos.entities.Cliente;

import jakarta.validation.constraints.Email;
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
public class ClienteDTO {

	private Long id;
	
	@NotBlank(message = "Campo obrigatório")
	@Size(min = 3, max = 80, message = "deve ter entre 3 e 60 caracteres")
	private String nome;
	
	@Email(message = "favor entrar com email válido")
	private String email;
	
	@CPF(message = "favor entrar com CPF válido")
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
