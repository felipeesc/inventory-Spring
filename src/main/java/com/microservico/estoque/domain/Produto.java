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
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_produto")
    private Long codigoProduto;

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
