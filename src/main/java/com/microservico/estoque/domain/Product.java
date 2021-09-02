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
@Table(name = "tb_produto")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_produto")
    private Long codigoProduto;

    //owner
    @ManyToOne
    @JoinColumn(name ="codigo_categoria")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "codigo_fornecedor")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "produtos")
    private InputItem inputItem;

    @OneToOne
    @JoinColumn(name = "produto")
    private OutputItem outputItem;

    private String descricao;
    private Double peso;
    private Boolean controlador;

    @Column(name = "quantidade_minima")
    private Integer quantidadeMinima;


}
