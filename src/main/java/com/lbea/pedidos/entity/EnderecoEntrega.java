package com.lbea.pedidos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_endereco_entrega")
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class EnderecoEntrega {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String rua;
	private String cidade;
	private String cep;

	@OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
