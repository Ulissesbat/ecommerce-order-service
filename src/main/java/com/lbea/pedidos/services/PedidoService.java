package com.lbea.pedidos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lbea.pedidos.dto.ConverterDTO;
import com.lbea.pedidos.dto.EnderecoEntregaDTO;
import com.lbea.pedidos.dto.PedidoDTO;
import com.lbea.pedidos.entities.Cliente;
import com.lbea.pedidos.entities.EnderecoEntrega;
import com.lbea.pedidos.entities.ItemPedido;
import com.lbea.pedidos.entities.Pagamento;
import com.lbea.pedidos.entities.Pedido;
import com.lbea.pedidos.entities.Produto;
import com.lbea.pedidos.enums.PedidoStatus;
import com.lbea.pedidos.repositories.ClienteRepository;
import com.lbea.pedidos.repositories.PedidoRepository;
import com.lbea.pedidos.repositories.ProdutoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;
    private final ConverterDTO converterDTO;

    public PedidoService(PedidoRepository pedidoRepository, 
                        ProdutoRepository produtoRepository,
                        ClienteRepository clienteRepository,
                        
                        ConverterDTO converterDTO) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
        this.converterDTO = converterDTO;
    }

    @Transactional
    public PedidoDTO criarPedido(PedidoDTO dto) {
        // Cria entidade Pedido
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus(PedidoStatus.PENDENTE);

        // Mapeia Cliente
        if(dto.getClienteId() != null) {
        	Cliente cliente = clienteRepository.findById(dto.getClienteId())
        	        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        	    pedido.setCliente(cliente);
        }

        // Mapeia Itens
        List<ItemPedido> itens = dto.getItens().stream().map(itemDto -> {
            ItemPedido item = new ItemPedido();
            Produto produto = produtoRepository.getReferenceById(itemDto.getProdutoId());
            item.setProduto(produto);
            item.setQuantidade(itemDto.getQuantidade());
            item.setPreco(itemDto.getPreco());
            item.setPedido(pedido);
            return item;
        }).collect(Collectors.toList());
        pedido.setItens(itens);

        // Mapeia Pagamento
        if(dto.getPagamento() != null) {
            Pagamento pagamento = new Pagamento();
            pagamento.setTipo(dto.getPagamento().getTipo());
            pagamento.setPagamentoStatus(dto.getPagamento().getPagamentoStatus());
            pagamento.setPedidoStatus(dto.getPagamento().getStatusPedido());
            pagamento.setData(dto.getPagamento().getData());
            pagamento.setPedido(pedido);
            pedido.setPagamento(pagamento);
        }

        // Mapeia Endereço de Entrega
        if(dto.getEnderecoEntrega() != null) {
            EnderecoEntrega endereco = new EnderecoEntrega();
            endereco.setRua(dto.getEnderecoEntrega().getRua());
            endereco.setNumero(dto.getEnderecoEntrega().getNumero());
            endereco.setCidade(dto.getEnderecoEntrega().getCidade());
            endereco.setCep(dto.getEnderecoEntrega().getCep());
            endereco.setPedido(pedido);
            pedido.setEnderecoEntrega(endereco);
        }

        // Salva no banco
        Pedido salvo = pedidoRepository.save(pedido);

        // Retorna DTO usando o ConverterDTO
        return converterDTO.converterPedidoParaDTO(salvo);
    }
}