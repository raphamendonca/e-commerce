package br.com.letscode.ecommerce.produto.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoFiltros {

    private String nome;
    private BigDecimal valor;
    private Integer peso;
    private String categoria;
    private String localArmazem;
}
