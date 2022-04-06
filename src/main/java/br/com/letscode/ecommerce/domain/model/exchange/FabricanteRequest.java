package br.com.letscode.ecommerce.domain.model.exchange;


import br.com.letscode.ecommerce.domain.model.entity.FabricanteEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FabricanteRequest {

    private String nome;

    public FabricanteEntity toEntity(){
        return FabricanteEntity.builder()
                .nome(this.nome)
                .dataCriacao(ZonedDateTime.now())
                .dataAtualizacao(ZonedDateTime.now())
                .build();
    }

}
