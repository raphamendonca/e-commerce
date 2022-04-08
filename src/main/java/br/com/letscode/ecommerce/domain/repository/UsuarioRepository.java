package br.com.letscode.ecommerce.domain.repository;

import br.com.letscode.ecommerce.domain.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository extends JpaRepository<UsuarioEntity,Long>, JpaSpecificationExecutor<UsuarioEntity> {
    @Query(value = "SELECT * FROM USUARIO where nome = ?1", nativeQuery = true)
    UsuarioEntity findByName(String nome);

}
