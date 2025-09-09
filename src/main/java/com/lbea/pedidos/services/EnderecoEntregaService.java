package com.lbea.pedidos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lbea.pedidos.dto.EnderecoEntregaDTO;
import com.lbea.pedidos.entities.EnderecoEntrega;
import com.lbea.pedidos.entities.Pedido;
import com.lbea.pedidos.repositories.EndereçoEntregaRepository;
import com.lbea.pedidos.repositories.PedidoRepository;

@Service
public class EnderecoEntregaService {
	
	@Autowired
	private EndereçoEntregaRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;

    @Transactional
    public EnderecoEntregaDTO atualizarEnderecoPorPedidoId(Long pedidoId, EnderecoEntregaDTO dto) {
        // Busca o pedido
        Pedido pedido = pedidoRepository.findById(pedidoId)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + pedidoId));

        // Verifica se o pedido já tem um endereço de entrega
        EnderecoEntrega enderecoExistente = pedido.getEnderecoEntrega();
        
        if (enderecoExistente == null) {
            // Se não existir, cria um novo endereço
            EnderecoEntrega novoEndereco = new EnderecoEntrega();
            copyDtoToEntity(dto, novoEndereco);
            novoEndereco.setPedido(pedido);
            
            EnderecoEntrega enderecoSalvo = enderecoRepository.save(novoEndereco);
            pedido.setEnderecoEntrega(enderecoSalvo);
            pedidoRepository.save(pedido);
            
            return new EnderecoEntregaDTO(enderecoSalvo);
        } else {
            // Se existir, atualiza o endereço existente
            copyDtoToEntity(dto, enderecoExistente);
            EnderecoEntrega enderecoAtualizado = enderecoRepository.save(enderecoExistente);
            return new EnderecoEntregaDTO(enderecoAtualizado);
        }
    }

	private void copyDtoToEntity(EnderecoEntregaDTO dto, EnderecoEntrega entity) {
		entity.setRua(dto.getRua());
		entity.setCidade(dto.getCidade());
		entity.setCep(dto.getCep());
		entity.setNumero(dto.getNumero());
		
	}
}
