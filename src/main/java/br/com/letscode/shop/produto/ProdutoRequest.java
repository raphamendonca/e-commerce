package br.com.letscode.shop.produto;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProdutoRequest {

    private String nome;

    private String descricao;

    private BigDecimal valor;

    private String codigoBarra;

    private Long idFabricante;

    private Integer peso;

    private String pesoUnidadeMedida;

}
