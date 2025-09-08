package com.lbea.pedidos.dto;

import java.time.LocalDateTime;

import com.lbea.pedidos.entities.Pagamento;
import com.lbea.pedidos.enums.PagamentoStatus;
import com.lbea.pedidos.enums.PedidoStatus;
import com.lbea.pedidos.enums.TipoPagamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PagamentoDTO {
    private Long id;
    private TipoPagamento tipo;
    private PagamentoStatus pagamentoStatus;
    private PedidoStatus statusPedido;
    private LocalDateTime data;

    public PagamentoDTO(Pagamento entity) {
        this.id = entity.getId();
        this.tipo = entity.getTipo();
        this.pagamentoStatus = entity.getPagamentoStatus();
        this.statusPedido = entity.getPedidoStatus();
        this.data = entity.getData();
    }
}