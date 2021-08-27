package com.microservico.estoque.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Table(name = "tb_item_entrada")
public class ItemEntrada implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_item_entrada")
    private Integer codigoItemEntrada;

    @OneToMany(mappedBy = "itemEntrada")
    @Column(name = "codigo_produto")
    private List<Produto> produtos;

    @OneToOne
    @JoinColumn(name = "itemEntrada")
    private Entrada entrada;

    private String lote;
    private Integer quantidade;
    private BigDecimal valor;

}
