package com.lbea.pedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lbea.pedidos.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long >{

}
