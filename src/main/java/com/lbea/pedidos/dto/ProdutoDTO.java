package com.lbea.pedidos.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.lbea.pedidos.entities.Categoria;
import com.lbea.pedidos.entities.Produto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoDTO {
    private Long id;
    
    @NotBlank(message = "Campo obrigatório")
	@Size(min = 3, max = 80, message = "deve ter entre 3 e 60 caracteres")
    private String nome;
    
    @NotNull(message = "O preço não pode ser nulo")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
    @Digits(integer = 10, fraction = 2, message = "O preço deve ter no máximo 10 dígitos inteiros e 2 casas decimais")
    private BigDecimal preco;

    @Min(value = 0, message = "O estoque não pode ser negativo")
    @Max(value = 100000, message = "O estoque não pode ultrapassar 100000 unidades")
    private int estoque;

    private Set<CategoriaDTO> categorias = new HashSet<>();

    public ProdutoDTO(Produto entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.preco = entity.getPreco();
        this.estoque = entity.getEstoque();
        
        for(Categoria cat : entity.getCategorias()) {
        	categorias.add(new CategoriaDTO(cat));
        }
    }
}
