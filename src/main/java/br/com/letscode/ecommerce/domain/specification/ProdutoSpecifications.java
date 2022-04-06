package br.com.letscode.ecommerce.domain.specification;

import br.com.letscode.ecommerce.domain.model.entity.ProdutoEntity;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProdutoSpecifications {

    public static Specification<ProdutoEntity> valorMenorQue(BigDecimal valor) {
        return valor == null ? null : (produtoEntity, cq, cb) -> cb.lessThanOrEqualTo(produtoEntity.get("valor"),valor);
    }
    public static Specification<ProdutoEntity> nomeContem(String nome) {
        return nome == null ? null : (produtoEntity, cq, cb) -> cb.like(produtoEntity.get("nome"), "%" + nome + "%");
    }
}
