package br.com.letscode.ecommerce.domain.service;

import br.com.letscode.ecommerce.domain.model.entity.UsuarioEntity;
import br.com.letscode.ecommerce.domain.model.exchange.UsuarioFiltrosRequest;
import br.com.letscode.ecommerce.domain.model.exchange.UsuarioRequest;
import br.com.letscode.ecommerce.domain.model.pagination.OffsetLimitPageable;
import br.com.letscode.ecommerce.domain.model.entity.FabricanteEntity;
import br.com.letscode.ecommerce.domain.repository.FabricanteRepository;
import br.com.letscode.ecommerce.domain.repository.ProdutoRepository;
import br.com.letscode.ecommerce.domain.repository.UsuarioRepository;
import br.com.letscode.ecommerce.domain.specification.ProdutoSpecifications;
import br.com.letscode.ecommerce.domain.model.entity.ProdutoEntity;
import br.com.letscode.ecommerce.domain.model.exchange.ProdutoFiltrosRequest;
import br.com.letscode.ecommerce.domain.model.exchange.ProdutoRequest;
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

    public UsuarioEntity buscarPorId(Long id) {
        return usuarioRepository.findById(id).get();//TODO adicionar tratativa para optional empty
    }

   /* public UsuarioEntity buscarPorCodigoBarra(String codigoBarra){
        return usuarioRepository.findByCodigoBarra(codigoBarra);
    }*/

    public UsuarioEntity criar(UsuarioRequest usuarioRequest) {

        //valida descricao, sanitizacao... retira HTML, scripts de campos texto. pode ser na view.
        //Optional<FabricanteEntity> fabricanteEntity = fabricanteRepository.findById(produtoRequest.getIdFabricante());
        //TODO implementar exception para o sistema
        UsuarioEntity usuarioEntity = toEntity(usuarioRequest);

        return UsuarioRepository.save(usuarioEntity);
    }

    private UsuarioEntity toEntity(UsuarioRequest usuarioRequest) {
        return new UsuarioEntity(
                usuarioRequest.getNome()

        );
    }
}
