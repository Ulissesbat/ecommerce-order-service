package com.lbea.pedidos.dto;

import java.time.LocalDateTime;

import com.lbea.pedidos.entities.Pedido;
import com.lbea.pedidos.enums.PedidoStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoMinDTO {
	
	private Long id;
    private LocalDateTime data;
    private PedidoStatus status;


    public PedidoMinDTO(Pedido entity) {
        id = entity.getId();
        data = entity.getData();
        status = entity.getStatus();
    }

}
