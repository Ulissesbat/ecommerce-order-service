package com.lbea.pedidos.entities;

import java.time.LocalDateTime;

import com.lbea.pedidos.enums.PagamentoStatus;
import com.lbea.pedidos.enums.PedidoStatus;
import com.lbea.pedidos.enums.TipoPagamento;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pagamento")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Pagamento {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Enumerated(EnumType.STRING)
	    private TipoPagamento tipo;
	    
	    @Enumerated(EnumType.STRING)
	    private PagamentoStatus pagamentoStatus; 
	    
	    @Enumerated(EnumType.STRING)
	    private PedidoStatus pedidoStatus; 
	    
	    private LocalDateTime data;
	    
	    @OneToOne
	    @JoinColumn(name = "pedido_id")
	    private Pedido pedido;

}
