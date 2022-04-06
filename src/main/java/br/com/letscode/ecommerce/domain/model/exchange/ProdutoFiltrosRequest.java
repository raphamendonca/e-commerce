package br.com.letscode.ecommerce.domain.model.exchange;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoFiltrosRequest {

    private String nome;
    private BigDecimal valor;
    private Integer peso;
    private String categoria;
    private String localArmazem;
}
