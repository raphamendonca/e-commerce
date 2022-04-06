package br.com.letscode.ecommerce.produto;

import br.com.letscode.ecommerce.domain.model.entity.FabricanteEntity;
import br.com.letscode.ecommerce.domain.repository.FabricanteRepository;
import br.com.letscode.ecommerce.domain.model.entity.ProdutoEntity;
import br.com.letscode.ecommerce.domain.model.exchange.ProdutoFiltrosRequest;
import br.com.letscode.ecommerce.domain.repository.ProdutoRepository;
import br.com.letscode.ecommerce.domain.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

@AutoConfigureTestDatabase
@ExtendWith(SpringExtension.class) // ExtendWith = JUNIT 5 | RunWith = JUnit4
@SpringBootTest
public class ProdutoListagemTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FabricanteRepository fabricanteRepository;

    @Autowired
    private EntityManager entityManager;

    private ProdutoService service;

    @BeforeEach
    void initTest(){
        service = new ProdutoService(produtoRepository,
                fabricanteRepository,
                entityManager);
    }

    @Test
    public void TesteListagemPaginacaoSucesso(){
        FabricanteEntity fabricanteEntity = fabricanteRepository.save(
                FabricanteEntity.builder().nome("Fabrica de Teste").build());

        ProdutoEntity produtoEntity = new ProdutoEntity(
                "Pulseira ","",new BigDecimal("20.00"),
                "k1jh2kj3h1k2j23h",fabricanteEntity , 10,
                "g");

        produtoRepository.save(produtoEntity);

        ProdutoFiltrosRequest filtros = new ProdutoFiltrosRequest();

        Page<ProdutoEntity> resultado = service.buscarTodos(0, 1, filtros);
        Assert.isTrue(resultado.getContent().size()==1, "Resultado esperado: 1  resultado obtido "+ resultado.getContent().size());

        filtros.setNome("Camiseta");

        resultado = service.buscarTodos(0, 1, filtros);
        Assert.isTrue(resultado.getContent().size()==0, "Resultado esperado: 1  resultado obtido "+ resultado.getContent().size());
    }


    @Test
    public void TesteListagemPaginacaoComFiltroSucesso(){
        FabricanteEntity fabricanteEntity = fabricanteRepository.save(
                FabricanteEntity.builder().nome("Fabrica de Teste2").build());

        ProdutoEntity produtoEntity = new ProdutoEntity(
                "Pulseira 1","",new BigDecimal("20.00"),
                "k1jh2kj3h1k2j23h",fabricanteEntity , 10,
                "g");

        produtoRepository.save(produtoEntity);

        ProdutoFiltrosRequest filtros = new ProdutoFiltrosRequest();
        filtros.setNome("Camiseta");

        Page<ProdutoEntity> resultado = service.buscarTodos(0, 1, filtros);
        Assert.isTrue(resultado.getContent().size()==0, "Resultado esperado: 1  resultado obtido "+ resultado.getContent().size());
    }
}
