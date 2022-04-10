package br.com.letscode.ecommerce.domain.model.exchange;


import lombok.Data;

@Data
public class AutenticacaoRequest {

    private String usuario;
    private String senha;
}
