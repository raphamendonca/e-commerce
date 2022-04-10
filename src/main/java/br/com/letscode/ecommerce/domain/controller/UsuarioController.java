package br.com.letscode.ecommerce.domain.controller;

import br.com.letscode.ecommerce.domain.model.entity.UsuarioEntity;
import br.com.letscode.ecommerce.domain.model.exchange.ProdutoFiltrosRequest;
import br.com.letscode.ecommerce.domain.model.exchange.ProdutoRequest;
import br.com.letscode.ecommerce.domain.model.exchange.UsuarioFiltrosRequest;
import br.com.letscode.ecommerce.domain.model.exchange.UsuarioRequest;
import br.com.letscode.ecommerce.domain.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@AllArgsConstructor
@RequestMapping("usuarios")
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


        Page<UsuarioEntity> usuarios = usuarioService.buscarTodos(offset, limit, filtros);
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioEntity> create(
            @RequestBody UsuarioRequest request
    ){
        UsuarioEntity usuario = usuarioService.criar(request);
        return ResponseEntity.created(null).body(usuario);
    }

    @PutMapping("{id}")
    public ResponseEntity<UsuarioEntity> update(
            @PathVariable("id") Long id,
            @RequestBody UsuarioRequest request
    ){
        UsuarioEntity usuario = usuarioService.atualizar(id, request);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<UsuarioEntity> getById(
            @PathVariable(name = "id") Long id) {

        UsuarioEntity usuarioBusca = usuarioService.buscarPorId(id);

        return ResponseEntity.ok(usuarioBusca);
    }

    @GetMapping("/usuario/{nome}")
    public ResponseEntity<UsuarioEntity> getByName(
            @PathVariable(name = "nome") String nome) {

        UsuarioEntity usuarioBuscaNome = usuarioService.buscarPorNome(nome);

        return ResponseEntity.ok(usuarioBuscaNome);
    }

    @DeleteMapping("/usuario/{id}")
    public String deleteUserById(
            @PathVariable(name = "id") Long id){

        usuarioService.apagarPorId(id);

        return "The user has been deleted.";
    }
}
