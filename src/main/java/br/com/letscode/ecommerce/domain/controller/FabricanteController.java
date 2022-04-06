package br.com.letscode.ecommerce.domain.controller;

import br.com.letscode.ecommerce.domain.service.FabricanteService;
import br.com.letscode.ecommerce.domain.model.exchange.FabricanteRequest;
import br.com.letscode.ecommerce.domain.model.entity.FabricanteEntity;
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
