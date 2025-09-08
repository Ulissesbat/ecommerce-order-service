package com.lbea.pedidos.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lbea.pedidos.dto.EnderecoEntregaDTO;
import com.lbea.pedidos.entity.services.Exceptions.ResourceNotFoundException;
import com.lbea.pedidos.services.EnderecoEntregaService;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoEntregaController {
	
	@Autowired
	private EnderecoEntregaService service;
	
	@PostMapping
	public ResponseEntity<EnderecoEntregaDTO> insert(@RequestBody EnderecoEntregaDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EnderecoEntregaDTO> update(@PathVariable Long id, @RequestBody EnderecoEntregaDTO dto) {
	    try {
	    	EnderecoEntregaDTO newDto = service.update(id, dto);
	        return ResponseEntity.ok().body(newDto);
	    } catch (ResourceNotFoundException e) {
	        return ResponseEntity.notFound().build();
	    }
	}
	
}

