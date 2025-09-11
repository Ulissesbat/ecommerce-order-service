package com.lbea.pedidos.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.lbea.pedidos.entities.Cliente;
import com.lbea.pedidos.entities.EnderecoEntrega;
import com.lbea.pedidos.entities.ItemPedido;
import com.lbea.pedidos.entities.Pagamento;
import com.lbea.pedidos.entities.Pedido;
import com.lbea.pedidos.entities.Produto;

@Component
public class ConverterDTO {
    
    public ClienteDTO converterClienteParaDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setEmail(cliente.getEmail());
        dto.setCpf(cliente.getCpf());
        // NÃO mapeamos a lista de pedidos para evitar lazy loading
        return dto;
    }

    public PagamentoDTO converterPagamentoParaDTO(Pagamento pagamento) {
        if (pagamento == null) {
            return null;
        }
        
        // Usando o construtor que já faz o mapeamento correto
        return new PagamentoDTO(pagamento);
    }

    public EnderecoEntregaDTO converterEnderecoParaDTO(EnderecoEntrega endereco) {
        if (endereco == null) {
            return null;
        }
        
        EnderecoEntregaDTO dto = new EnderecoEntregaDTO();
        dto.setId(endereco.getId());
        dto.setRua(endereco.getRua());
        dto.setNumero(endereco.getNumero());
        dto.setCidade(endereco.getCidade());
        dto.setCep(endereco.getCep());
        return dto;
    }

    public ItemPedidoDTO converterItemPedidoParaDTO(ItemPedido item) {
        if (item == null) {
            return null;
        }
        
        ItemPedidoDTO dto = new ItemPedidoDTO();
        dto.setProdutoId(item.getProduto().getId());
        dto.setQuantidade(item.getQuantidade());
        dto.setPreco(item.getPreco());
        
        // Converte o produto
        if (item.getProduto() != null) {
        	dto.setProdutoId(item.getProduto().getId());
        }
        
        return dto;
    }

    public ProdutoDTO converterProdutoParaDTO(Produto produto) {
        if (produto == null) {
            return null;
        }
        
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setPreco(produto.getPreco());
        dto.setEstoque(produto.getEstoque());
       
        return dto;
    }

    public PedidoDTO converterPedidoParaDTO(Pedido pedido) {
        if (pedido == null) {
            return null;
        }
        
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setData(pedido.getData());
        dto.setStatus(pedido.getStatus());
        
        // Converte cliente
        dto.setClienteId(pedido.getCliente().getId());
        
        // Converte pagamento
        dto.setPagamento(converterPagamentoParaDTO(pedido.getPagamento()));
        
        // Converte endereço de entrega
        dto.setEnderecoEntrega(converterEnderecoParaDTO(pedido.getEnderecoEntrega()));
        
        // Converte itens do pedido
        if (pedido.getItens() != null && !pedido.getItens().isEmpty()) {
            List<ItemPedidoDTO> itensDTO = pedido.getItens().stream()
                .map(this::converterItemPedidoParaDTO)
                .collect(Collectors.toList());
            dto.setItens(itensDTO);
        }
        
        return dto;
    }
}