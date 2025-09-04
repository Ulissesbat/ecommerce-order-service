package com.lbea.pedidos.entity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lbea.pedidos.dto.EnderecoEntregaDTO;
import com.lbea.pedidos.entities.EnderecoEntrega;
import com.lbea.pedidos.entity.services.Exceptions.ResourceNotFoundException;
import com.lbea.pedidos.repositories.EndereçoEntregaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EnderecoEntregaService {
	
	@Autowired
	private EndereçoEntregaRepository repository;
	
	@Transactional
	public EnderecoEntregaDTO insert(EnderecoEntregaDTO dto) {
		EnderecoEntrega entity = new EnderecoEntrega();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new EnderecoEntregaDTO (entity);
	}
	
	@Transactional
	public EnderecoEntregaDTO update (Long id, EnderecoEntregaDTO dto) throws ResourceNotFoundException {
		
		try {
			EnderecoEntrega entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new EnderecoEntregaDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	private void copyDtoToEntity(EnderecoEntregaDTO dto, EnderecoEntrega entity) {
		entity.setRua(dto.getRua());
		entity.setCidade(dto.getCidade());
		entity.setCep(dto.getCep());
		entity.setNumero(dto.getNumero());
		
	}
}
