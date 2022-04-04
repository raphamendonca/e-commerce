package br.com.letscode.ecommerce.produto;

import br.com.letscode.ecommerce.fabricante.FabricanteEntity;
import br.com.letscode.ecommerce.fabricante.FabricanteRepository;
import br.com.letscode.ecommerce.produto.models.ProdutoEntity;
import br.com.letscode.ecommerce.produto.models.ProdutoRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

@AutoConfigureTestDatabase
@ExtendWith(SpringExtension.class) // ExtendWith = JUNIT 5 | RunWith = JUnit4
@SpringBootTest
public class ProdutoCriacaoTest {

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
    public void criacaoProdutoSucesso(){
        FabricanteEntity fabricanteEntity = fabricanteRepository.save(
                FabricanteEntity.builder().nome("Fabrica de Teste").build());

        ProdutoRequest request = new ProdutoRequest();
        request.setNome("Pulseira ");
        request.setCodigoBarra("k1jh2kj3h1k2j23h");
        request.setDescricao("");
        request.setIdFabricante(fabricanteEntity.getId());
        request.setValor(new BigDecimal("20.00"));

        ProdutoEntity entity = service.criar(request);

        Assert.notNull(entity, "Entity nula");
        Assert.notNull(entity.getId(), "Id Nulo");
        Assert.notNull(entity.getCodigo(), "Codigo Nulo");
        Assert.isTrue(entity.getNome().equals(request.getNome()),"Nome diferente do informado");
        Assert.notNull(entity.getDataCriacao(), "Data de Criacao nao pode ser Nulo");

    }

}
