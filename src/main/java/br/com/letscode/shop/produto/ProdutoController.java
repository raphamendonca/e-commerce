package br.com.letscode.shop.produto;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

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
//            @RequestParam(name = "dataCriacao") String dataCriacao,
//            @RequestParam(name = "fabricante") String fabricante,
            @RequestParam(name = "valor_maximo", required = false) BigDecimal valorMaximo
    ){
        Page<ProdutoEntity> produtos = produtoService.buscarTodos(offset, limit, nome, valorMaximo);
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
