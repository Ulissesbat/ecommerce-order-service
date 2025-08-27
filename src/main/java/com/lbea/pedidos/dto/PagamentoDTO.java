package com.lbea.pedidos.dto;

import com.lbea.pedidos.entities.Pagamento;
import com.lbea.pedidos.enums.TipoPagamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PagamentoDTO {
    private Long id;
    private TipoPagamento tipo;

    public PagamentoDTO(Pagamento entity) {
        this.id = entity.getId();
        this.tipo = entity.getTipo();
    }
}
