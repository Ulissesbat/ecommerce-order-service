package com.lbea.pedidos.dto;

import java.util.ArrayList;
import java.util.List;

import com.lbea.pedidos.entities.AuthUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthUserDTO {
	
	private Long id;
    private String email;            
    private String password;  
    
    private ClienteDTO clienteDTO;

	 private List<String> roles = new ArrayList<>();
	 
	 public AuthUserDTO(AuthUser entity) {
		    this.id = entity.getId();
		    this.email = entity.getEmail();
		    this.password = entity.getPassword();

		    if (entity.getCliente() != null) {
		        this.clienteDTO = new ClienteDTO(entity.getCliente());
		    }

		    entity.getRoles().forEach(role -> this.roles.add(role.getAuthority()));
		}


}
