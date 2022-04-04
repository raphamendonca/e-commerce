package br.com.letscode.ecommerce.produto;

import br.com.letscode.ecommerce.produto.models.ProdutoEntity;
import br.com.letscode.ecommerce.produto.models.ProdutoFiltros;
import br.com.letscode.ecommerce.produto.models.ProdutoRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@AllArgsConstructor
@RequestMapping("produtos")
@RestController
public class ProdutoController {

    private ProdutoService produtoService;

    @GetMapping()
    public ResponseEntity<Page<ProdutoEntity>> get(
            @RequestParam(name = "offset") Integer offset,
            @RequestParam(name = "limit") Integer limit,
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "valor_maximo", required = false) BigDecimal valorMaximo
    ){
        ProdutoFiltros filtros = new ProdutoFiltros();
        filtros.setNome(nome);
        filtros.setValor(valorMaximo);

        Page<ProdutoEntity> produtos = produtoService.buscarTodos(offset, limit, filtros);
        return ResponseEntity.ok(produtos);
    }

//    @Secured("USER")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProdutoEntity> create(
             @RequestBody ProdutoRequest request
    ){
        ProdutoEntity produto = produtoService.criar(request);
        return ResponseEntity.created(null).body(produto);
    }

    @GetMapping("/codigo/{codigoBarra}")
    public ResponseEntity<ProdutoEntity> getByCodigoBarra(
            @PathVariable(name = "codigoBarra") String codigoBarra){

        ProdutoEntity produto = produtoService.buscarPorCodigoBarra(codigoBarra);
        return ResponseEntity.ok(produto);
    }
}
