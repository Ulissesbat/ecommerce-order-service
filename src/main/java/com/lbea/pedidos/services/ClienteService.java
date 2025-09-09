package com.lbea.pedidos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lbea.pedidos.dto.ClienteDTO;
import com.lbea.pedidos.entities.Cliente;
import com.lbea.pedidos.entity.services.Exceptions.DatabaseException;
import com.lbea.pedidos.entity.services.Exceptions.ResourceNotFoundException;
import com.lbea.pedidos.repositories.ClienteRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {
	
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

	
	private void copyDtoToEntity(ClienteDTO dto, Cliente entity) {
		entity.setNome(dto.getNome());
		entity.setCpf(dto.getCpf());
		entity.setEmail(dto.getEmail());
	}

}
