package com.lbea.pedidos.entity.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lbea.pedidos.dto.CategoriaDTO;
import com.lbea.pedidos.dto.ProdutoDTO;
import com.lbea.pedidos.entities.Categoria;
import com.lbea.pedidos.entities.Produto;
import com.lbea.pedidos.entity.services.Exceptions.DatabaseException;
import com.lbea.pedidos.entity.services.Exceptions.ResourceNotFoundException;
import com.lbea.pedidos.repositories.CategoriaRepository;
import com.lbea.pedidos.repositories.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Transactional(readOnly = true)
	public Page<ProdutoDTO> findAllPaged(Pageable pageable) {
		Page<Produto> list = produtoRepository.findAll(pageable);
		return list.map(x -> new ProdutoDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ProdutoDTO findById(Long id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		Produto entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ProdutoDTO(entity);
	}
	
	@Transactional
	public ProdutoDTO insert(ProdutoDTO dto) {
		Produto entity = new Produto();
		copyDtoToEntity(dto, entity);
		entity = produtoRepository.save(entity);
		return new ProdutoDTO (entity);
	}
	
	@Transactional
	public ProdutoDTO update (Long id, ProdutoDTO dto) throws ResourceNotFoundException {
		
		try {
			Produto entity = produtoRepository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = produtoRepository.save(entity);
			return new ProdutoDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	@Transactional
	public void delete(Long id) {
	    if (!produtoRepository.existsById(id)) {
	        throw new ResourceNotFoundException("Id not found " + id);
	    }
	    try {
	    	produtoRepository.deleteById(id);
	    } catch (DataIntegrityViolationException e) {
	        throw new DatabaseException("Integrity violation");
	    }
	}
	
	private void copyDtoToEntity(ProdutoDTO dto, Produto entity) {
	    entity.setNome(dto.getNome());
	    entity.setPreco(dto.getPreco());
	    entity.setEstoque(dto.getEstoque());
	    
	    entity.getCategorias().clear();

	    for (CategoriaDTO catDto : dto.getCategorias()) {
	        Categoria categoria = categoriaRepository.getReferenceById(catDto.getId());
	        entity.getCategorias().add(categoria);
	    }

	}

	

}
