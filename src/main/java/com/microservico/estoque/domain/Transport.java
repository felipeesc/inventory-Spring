package com.microservico.estoque.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Table(name = "tb_transportadora")
public class Transport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_transportadora")
    private Long codigoTransportadora;

    @ManyToOne
    @JoinColumn(name = "codigoCidade")
    private City cidades;

    @ManyToOne
    @JoinColumn(name = "codigo_entrada")
    private Input input;

    private String transportadora;

    @Embedded
    private Address address;
}
