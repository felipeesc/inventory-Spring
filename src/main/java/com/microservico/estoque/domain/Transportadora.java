package com.microservico.estoque.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Table(name = "tb_transportadora")
public class Transportadora implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_transportadora")
    private Long codigoTransportadora;

    @ManyToOne
    @JoinColumn(name = "codigoCidade")
    private Cidade cidades;

    @ManyToOne
    @JoinColumn(name = "codigo_entrada")
    private Entrada entrada;

    private String transportadora;

    @Embedded
    private Endereco endereco;
}
