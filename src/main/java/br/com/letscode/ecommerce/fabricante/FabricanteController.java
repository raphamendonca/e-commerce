package br.com.letscode.ecommerce.fabricante;

import br.com.letscode.ecommerce.produto.models.ProdutoEntity;
import br.com.letscode.ecommerce.produto.models.ProdutoRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("fabricantes")
@RestController
public class FabricanteController {

    private FabricanteService fabricanteService;

    @PostMapping
    public ResponseEntity<FabricanteEntity> create(
            @RequestBody FabricanteRequest request
    ){
        return ResponseEntity.created(null).body(fabricanteService.criar(request));
    }

}
