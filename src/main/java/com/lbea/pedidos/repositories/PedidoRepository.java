package com.lbea.pedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lbea.pedidos.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
