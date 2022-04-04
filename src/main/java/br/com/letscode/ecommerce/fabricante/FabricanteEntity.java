package br.com.letscode.ecommerce.fabricante;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.ZonedDateTime;

@Entity(name = "FABRICANTE")
@Data
@Builder
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
