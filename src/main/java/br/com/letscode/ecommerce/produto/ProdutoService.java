package br.com.letscode.ecommerce.produto;

import br.com.letscode.ecommerce.fabricante.FabricanteEntity;
import br.com.letscode.ecommerce.fabricante.FabricanteRepository;
import br.com.letscode.ecommerce.produto.models.OffsetLimitPageable;
import br.com.letscode.ecommerce.produto.models.ProdutoEntity;
import br.com.letscode.ecommerce.produto.models.ProdutoFiltros;
import br.com.letscode.ecommerce.produto.models.ProdutoRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
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
                                           ProdutoFiltros filtros) {

        Pageable pageable = new OffsetLimitPageable(offset, limit);

        return produtoRepository.findAll(
                where(ProdutoSpecifications.nomeContem(filtros.getNome()))
                 .and(ProdutoSpecifications.valorMenorQue(filtros.getValor())),
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

        //valida descricao, sanitizacao... retira HTML, scripts de campos texto. pode ser na view.
        Optional<FabricanteEntity> fabricanteEntity = fabricanteRepository.findById(produtoRequest.getIdFabricante());
        //TODO implementar exception para o sistema
        ProdutoEntity produtoEntity = toEntity(produtoRequest, fabricanteEntity.get());

        return produtoRepository.save(produtoEntity);
    }

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

}
