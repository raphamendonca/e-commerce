package br.com.letscode.ecommerce.domain.model.exchange;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProdutoRequest {

    //notblank
    private String nome;

// @Sanitize
    private String descricao;


    private BigDecimal valor;

//    @Pattern("")
    private String codigoBarra;

    private Long idFabricante;

//    @Positive
    private Integer peso;

//    @valida enum
    private String pesoUnidadeMedida;

}
