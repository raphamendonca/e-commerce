package br.com.letscode.ecommerce.domain.model.exchange;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AutenticacaoResponse {
    private String token;
}
