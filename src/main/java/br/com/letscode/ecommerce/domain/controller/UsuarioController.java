package br.com.letscode.ecommerce.domain.controller;

import br.com.letscode.ecommerce.domain.model.entity.UsuarioEntity;
import br.com.letscode.ecommerce.domain.model.exchange.ProdutoFiltrosRequest;
import br.com.letscode.ecommerce.domain.model.exchange.ProdutoRequest;
import br.com.letscode.ecommerce.domain.model.exchange.UsuarioFiltrosRequest;
import br.com.letscode.ecommerce.domain.service.UsuarioService;
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
public class UsuarioController {

    private UsuarioService usuarioService;

    // http://localhost:8080/users/1/produtos?offset=0&limit=10
    // http://localhost:8080 => URL / API
    // /users/1/produtos => endpoint, path => path, 1=> pathVariable
    // ? offset=0&limit=10 => requestParam ou QueryParam
    // auth => header, ou seja, cabecalho


    @GetMapping
    public ResponseEntity<Page<UsuarioEntity>> get(
            @RequestParam(name = "offset") Integer offset,
            @RequestParam(name = "limit") Integer limit,
            @RequestParam(name = "nome", required = false) String nome
            ){
        UsuarioFiltrosRequest filtros = new UsuarioFiltrosRequest();
        filtros.setNome(nome);


        Page<UsuarioEntity> usuarios = UsuarioService.buscarTodos(offset, limit, filtros);
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioEntity> create(
            @RequestBody ProdutoRequest request
    ){
        UsuarioEntity usuario = usuarioService.criar(request);
        return ResponseEntity.created(null).body(usuario);
    }



}
