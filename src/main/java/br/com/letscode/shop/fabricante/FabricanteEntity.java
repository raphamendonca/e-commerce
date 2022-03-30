package br.com.letscode.shop.fabricante;

import br.com.letscode.shop.produto.ProdutoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.time.ZonedDateTime;
import java.util.List;

@Entity(name = "FABRICANTE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FabricanteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fabricante_seq")
    @SequenceGenerator(name = "fabricante_seq", sequenceName = "fabricante_seq")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "DATA_CRIACAO")
    private ZonedDateTime dataCriacao;

    @Column(name = "DATA_ATUALIZACAO")
    private ZonedDateTime dataAtualizacao;

}
