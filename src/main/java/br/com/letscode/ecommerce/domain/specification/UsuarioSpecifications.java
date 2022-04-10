package br.com.letscode.ecommerce.domain.specification;

import br.com.letscode.ecommerce.domain.model.entity.ProdutoEntity;
import br.com.letscode.ecommerce.domain.model.entity.UsuarioEntity;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class UsuarioSpecifications {

    public static Specification<UsuarioEntity> nomeContem(String nome) {
        return nome == null ? null : (usuarioEntity, cq, cb) -> cb.like(usuarioEntity.get("nome"), "%" + nome + "%");
    }
}
