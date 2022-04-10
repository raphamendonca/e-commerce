package br.com.letscode.ecommerce.domain.controller;

import br.com.letscode.ecommerce.domain.model.exchange.ProdutoFiltrosRequest;
import br.com.letscode.ecommerce.domain.model.exchange.ProdutoRequest;
import br.com.letscode.ecommerce.domain.model.entity.ProdutoEntity;
import br.com.letscode.ecommerce.domain.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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

    // http://localhost:8080/users/1/produtos?offset=0&limit=10
    // http://localhost:8080/users/1/produtos
    // ?offset=0&limit=10

    // http://localhost:8080 => URL / API
    // /users/1/produtos => endpoint, path => path, 1=> pathVariable
    // ? offset=0&limit=10 => requestParam ou QueryParam
    // auth => header, ou seja, cabecalho


    @GetMapping
    public ResponseEntity<Page<ProdutoEntity>> get(
            @RequestParam(name = "offset", required = false) Integer offset ,
            @RequestParam(name = "limit", required = false) Integer limit,
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "valor_maximo", required = false) BigDecimal valorMaximo
    ){

        ProdutoFiltrosRequest filtros = new ProdutoFiltrosRequest();
        filtros.setNome(nome);
        filtros.setValor(valorMaximo);

        Page<ProdutoEntity> produtos = produtoService.buscarTodos(offset, limit, filtros);
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
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
