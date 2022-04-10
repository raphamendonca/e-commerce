package br.com.letscode.ecommerce.domain.model.entity;

import br.com.letscode.ecommerce.domain.model.enums.ProdutoStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity(name = "USUARIO")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DATA_NASCIMENTO")
    private String dataNascimento;

    @Column(name = "DATA_CRIACAO")
    private ZonedDateTime dataCriacao;

    @Column(name = "DATA_ATUALIZACAO")
    private ZonedDateTime dataAtualizacao;


    public UsuarioEntity(String nome, String dataNascimento) {

        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.dataCriacao = ZonedDateTime.now();
        this.dataAtualizacao = ZonedDateTime.now();
    }

}
