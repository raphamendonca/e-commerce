package br.com.letscode.shop.produto;

import br.com.letscode.shop.fabricante.FabricanteEntity;
import br.com.letscode.shop.fabricante.FabricanteRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

@AllArgsConstructor
@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;
    private FabricanteRepository fabricanteRepository;

    private EntityManager entityManager;

    public Page<ProdutoEntity> buscarTodos(Integer offset,
                                           Integer limit,
                                           String nome,
//                                           String nomeFabricante,
                                           BigDecimal valor
    ) {
        Pageable pageable = new OffsetLimitPageable(offset, limit);

/*
    Filtro com Criteria
  */
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<ProdutoEntity> query = criteriaBuilder.createQuery(ProdutoEntity.class);
//        Root<ProdutoEntity> produto = query.from(ProdutoEntity.class);
//
//        List<Predicate> predicates = new ArrayList();
//
//        if (nome != null){
//            predicates.add(criteriaBuilder.like(produto.get("nome"), "%"+nome+"%"));
//        }
//
//        predicates.add(criteriaBuilder.or(
//                criteriaBuilder.equal(produto.get("nome"), "Pablo"),
//                criteriaBuilder.equal(produto.get("nome"), "Diogo")
//        ));
////        /**
////        select * from produto
////        where valor <= 12
////        and nome = Pablo or nome = Diogo
////        */
//
//        if(valor != null ) {
//            predicates.add(criteriaBuilder.lessThanOrEqualTo(produto.get("valor"), valor));
//        }
//
//        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
//
//        return entityManager.createQuery(query).getResultList();

 /*
    Filtro com Specification
  */
        return produtoRepository.findAll(
                where(nomeContem(nome)).and(valorMenorQue(valor)),
                pageable
        );
    }

    public ProdutoEntity buscarPorId(Long id){
        return produtoRepository.findById(id).get();//TODO adicionar tratativa para optional empty
    }

    public ProdutoEntity buscarPorCodigoBarra(String codigoBarra){
            return produtoRepository.findByCodigoBarra(codigoBarra);
    }

    public ProdutoEntity criar(ProdutoRequest produtoRequest){

        Optional<FabricanteEntity> fabricanteEntity = fabricanteRepository.findById(produtoRequest.getIdFabricante());
        //TODO implementar exception para o sistema
        ProdutoEntity produtoEntity = toEntity(produtoRequest, fabricanteEntity.get());

        return produtoRepository.save(produtoEntity);
    }

//    public ProdutoEntity atualizar(Long id, ProdutoRequest produtoRequest){
//
//        Optional<ProdutoEntity> optProdutoEntity = produtoRepository.findById(id);
//        //valido se recuperou ou 404
//        ProdutoEntity produtoEntity = optProdutoEntity.get();
//
//        produtoEntity.setValor(produtoRequest.getValor());
//
//        return produtoRepository.save(toEntity(re));
//    }

//    public ProdutoEntity buscarPorId(Long id){
//        return produtoRepository.findById(id).orElseThrow(() -> );
//    }

    private  ProdutoEntity toEntity(ProdutoRequest produtoRequest,
                                    FabricanteEntity fabricante){
        return new ProdutoEntity(
                produtoRequest.getNome(),
                produtoRequest.getDescricao(),
                produtoRequest.getValor(),
                produtoRequest.getCodigoBarra(),
                fabricante,
                produtoRequest.getPeso(),
                produtoRequest.getPesoUnidadeMedida()
        );
    }

    static Specification<ProdutoEntity> valorMenorQue(BigDecimal valor) {
        return valor == null ? null : (produtoEntity, cq, cb) -> cb.lessThanOrEqualTo(produtoEntity.get("valor"),valor);
    }
    static Specification<ProdutoEntity> nomeContem(String nome) {
        return nome == null ? null : (produtoEntity, cq, cb) -> cb.like(produtoEntity.get("nome"), "%" + nome + "%");
    }

}
