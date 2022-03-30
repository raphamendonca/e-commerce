package br.com.letscode.shop.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity,Long> , JpaSpecificationExecutor<ProdutoEntity> {

//    @Query("SELECT p from ProdutoEntity p where p.codigoBarra = :codigoBarra")
    @Query(value = "SELECT * FROM PRODUTO where codigo_barra = ?1", nativeQuery = true)
    ProdutoEntity findByCodigoBarra(String codigoBarra);

//    @Query(value = "SELECT sum(valor) FROM PRODUTO where id_fabricante = ?1", nativeQuery = true)
//    BigDecimal sumProductValuesByFabricante(Long idFabricante);
}