package br.com.letscode.ecommerce.domain.controller;


import br.com.letscode.ecommerce.domain.util.TokenUtil;
import br.com.letscode.ecommerce.domain.model.exchange.AutenticacaoRequest;
import br.com.letscode.ecommerce.domain.model.exchange.AutenticacaoResponse;
import br.com.letscode.ecommerce.domain.service.UsuarioDetalheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("autenticacao")
@RestController
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioDetalheService usuarioDetalheService;

    @Autowired
    private TokenUtil tokenUtil;

   @PostMapping
    public ResponseEntity<AutenticacaoResponse> login(
            @RequestBody AutenticacaoRequest request
    ) throws Exception {

       try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           request.getUsuario(),
                           request.getSenha()
                   ));
       }catch (DisabledException e){
           throw new Exception("Usuario Desabilitado", e);
       } catch (BadCredentialsException e){
           throw new Exception("Credenciais Inválidas", e);
       }

       UserDetails userDetails = usuarioDetalheService
               .loadUserByUsername(request.getUsuario());

       final String token = tokenUtil.gerarToken(userDetails);

       return ResponseEntity.ok(AutenticacaoResponse.builder()
               .token(token)
               .build());
    }
}
