package com.microservico.estoque.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Table(name = "tb_loja")
public class Loja implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_loja")
    private Integer codigoLoja;

    @ManyToOne
    @JoinColumn(name ="cidade")
    private Cidade cidade;

    private String nome;

    @Embedded
    private Endereco endereco;

}
