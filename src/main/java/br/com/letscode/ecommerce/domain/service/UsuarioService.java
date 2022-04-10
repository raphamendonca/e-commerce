package br.com.letscode.ecommerce.domain.service;

import br.com.letscode.ecommerce.domain.model.entity.UsuarioEntity;
import br.com.letscode.ecommerce.domain.model.exchange.UsuarioFiltrosRequest;
import br.com.letscode.ecommerce.domain.model.exchange.UsuarioRequest;
import br.com.letscode.ecommerce.domain.model.pagination.OffsetLimitPageable;
import br.com.letscode.ecommerce.domain.repository.UsuarioRepository;
import br.com.letscode.ecommerce.domain.specification.UsuarioSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

@AllArgsConstructor
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    //private FabricanteRepository fabricanteRepository;
    private EntityManager entityManager;

    public Page<UsuarioEntity> buscarTodos(Integer offset,
                                           Integer limit,
                                           UsuarioFiltrosRequest filtros) {

        Pageable pageable = new OffsetLimitPageable(offset, limit);

        return usuarioRepository.findAll(
                where(UsuarioSpecifications.nomeContem(filtros.getNome())),
                pageable
        );
    }

    //usar este caso o outro buscarPorId falhe.
//    public UsuarioEntity buscarPorId(Long id) {
//        return usuarioRepository.findById(id).get();
//    }

    public UsuarioEntity buscarPorId(Long id) {
        Optional<UsuarioEntity> usuarioResponse = usuarioRepository.findById(id);

        if(usuarioResponse.isPresent()) {
            usuarioResponse.get();
        } else {
            throw new RuntimeException("Não foi encontrado o id: " + id);
        }
        return usuarioResponse.get();
    }

    public UsuarioEntity buscarPorNome(String nome){
        return usuarioRepository.findByName(nome);
    }

    public void apagarPorId(Long id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioEntity criar(UsuarioRequest usuarioRequest) {

        //valida descricao, sanitizacao... retira HTML, scripts de campos texto. pode ser na view.
        //Optional<FabricanteEntity> fabricanteEntity = fabricanteRepository.findById(produtoRequest.getIdFabricante());
        //TODO implementar exception para o sistema
        UsuarioEntity usuarioEntity = toEntity(usuarioRequest);

        return usuarioRepository.save(usuarioEntity);
    }

    public UsuarioEntity atualizar(Long id, UsuarioRequest usuarioRequest) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);
        //UsuarioEntity usuarioEntity = toEntity(usuarioRequest);
        /*UsuarioEntity usuarioParaAtualizar = UsuarioEntity(
                id = usuario.getId(),
                nome = usuario.getNome(),
                dataNascimento = usuario.getDataNascimento()*/
        //);
        UsuarioEntity usuarioEntity = usuario.get();
        usuarioEntity.setNome(usuarioRequest.getNome());
        usuarioEntity.setDataNascimento(usuarioRequest.getDataNascimento());
        return usuarioRepository.save(usuarioEntity);
    }

    private UsuarioEntity toEntity(UsuarioRequest usuarioRequest) {
        return new UsuarioEntity(
                usuarioRequest.getNome(),
                usuarioRequest.getDataNascimento()

        );
    }
}
