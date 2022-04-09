package br.com.letscode.ecommerce.domain.service;

import br.com.letscode.ecommerce.domain.model.entity.UsuarioEntity;
import br.com.letscode.ecommerce.domain.repository.UsuarioAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsuarioDetalheService implements UserDetailsService {

    @Autowired
    private UsuarioAuthRepository repository;

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        UsuarioEntity usuarioEntity = repository.findByUsuario(usuario)
                .orElseThrow(() -> new UsernameNotFoundException(""+ usuario));

        return new User(usuario, usuarioEntity.getPassword(), new ArrayList<>());
    }
}
