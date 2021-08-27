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
@Table(name = "tb_produto")
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_produto")
    private Integer codigoProduot;

    //owner
    @ManyToOne
    @JoinColumn(name ="codigo_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "codigo_fornecedor")
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "produtos")
    private ItemEntrada itemEntrada;

    @OneToOne
    @JoinColumn(name = "produto")
    private ItemSaida itemSaida;

    private String descricao;
    private Double peso;
    private Boolean controlador;

    @Column(name = "quantidade_minima")
    private Integer quantidadeMinima;


}
