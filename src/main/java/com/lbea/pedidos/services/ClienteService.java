package com.lbea.pedidos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lbea.pedidos.dto.AuthUserDTO;
import com.lbea.pedidos.dto.ClienteDTO;
import com.lbea.pedidos.entities.AuthUser;
import com.lbea.pedidos.entities.Cliente;
import com.lbea.pedidos.entities.Role;
import com.lbea.pedidos.entity.services.Exceptions.DatabaseException;
import com.lbea.pedidos.entity.services.Exceptions.ResourceNotFoundException;
import com.lbea.pedidos.projections.UserDetailsProjection;
import com.lbea.pedidos.repositories.ClienteRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService implements UserDetailsService{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Transactional(readOnly = true)
	public Page<ClienteDTO> findAllPaged(Pageable pageable) {
		Page<Cliente> list = clienteRepository.findAll(pageable);
		return list.map(x -> new ClienteDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ClienteDTO findById(Long id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		Cliente entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found"));
		return new ClienteDTO(entity);
	}
	
	@Transactional
	public ClienteDTO insert(ClienteDTO dto) {
		Cliente entity = new Cliente();
		copyDtoToEntity(dto, entity);
		entity = clienteRepository.save(entity);
		return new ClienteDTO (entity);
	}
	
	@Transactional
	public ClienteDTO update (Long id, ClienteDTO dto) throws ResourceNotFoundException {
		
		try {
			Cliente entity = clienteRepository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = clienteRepository.save(entity);
			return new ClienteDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
	    if (!clienteRepository.existsById(id)) {
	        throw new ResourceNotFoundException("Id not found " + id);
	    }
	    try {
	        clienteRepository.deleteById(id);	        
	    } catch (DataIntegrityViolationException e) {
	        throw new DatabaseException("Integrity violation");
	    }
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserDetailsProjection> result = clienteRepository.searchUserAndRolesByEmail(username);
		if (result.size() == 0) {
			throw new UsernameNotFoundException("User not found");
		}
		 AuthUser authUser = new AuthUser();
		 authUser.setEmail(username);;
		 authUser.setPassword(result.get(0).getPassword());
		for (UserDetailsProjection projection : result) {
			authUser.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
		}
		return authUser; 
	}

	protected Cliente authenticated() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
			String username = jwtPrincipal.getClaim("username");
			return clienteRepository.findByEmail(username).get();
			
		} catch (Exception e) {
			throw new UsernameNotFoundException("User not found");
		}

	}
	@Transactional(readOnly = true)
	public AuthUserDTO getMe() {
	    Cliente cliente = authenticated();
	    return new AuthUserDTO(cliente.getAuthUser());
	}


	
	private void copyDtoToEntity(ClienteDTO dto, Cliente entity) {
		entity.setNome(dto.getNome());
		entity.setCpf(dto.getCpf());
		entity.setEmail(dto.getEmail());
	}

	

}
