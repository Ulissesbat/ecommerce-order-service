package com.lbea.pedidos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lbea.pedidos.dto.AuthUserDTO;
import com.lbea.pedidos.entities.AuthUser;
import com.lbea.pedidos.entities.Cliente;
import com.lbea.pedidos.repositories.AuthUserRepository;

@Service
public class AuthUserService {
	    
	    @Autowired
	    private AuthUserRepository authUserRepository;

	    protected Cliente authenticated() {
	        try {
	            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
	            String username = jwtPrincipal.getClaim("username");
	            AuthUser user = authUserRepository.findByEmail(username)
	                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	            return user.getCliente(); // pega o cliente do usuário autenticado
	        } catch (Exception e) {
	            throw new UsernameNotFoundException("User not found");
	        }
	    }

	    @Transactional(readOnly = true)
	    public AuthUserDTO getMe() {
	        Cliente cliente = authenticated();
	        return new AuthUserDTO(cliente.getAuthUser());
	    }

	    public void validationSelfOrAdmin(Long clienteId) {
	        Cliente cliente = authenticated();

	        // se não for admin e não for o dono do recurso → erro
	        boolean isAdmin = cliente.getAuthUser().getRoles()
	                .stream()
	                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

	        if (!isAdmin && !cliente.getId().equals(clienteId)) {
	            throw new AccessDeniedException("Acesso negado");
	        }
	    }
	}

