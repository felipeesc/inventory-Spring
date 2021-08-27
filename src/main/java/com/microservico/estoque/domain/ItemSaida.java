package com.microservico.estoque.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Table(name = "tb_item_saida")
public class ItemSaida implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_item_saida")
    private Integer codigoItemSaida;

    @ManyToOne
    @JoinColumn(name = "codigo_saida")
    private Saida saida;

    @OneToOne(mappedBy = "itemSaida")
    private Produto produto;

    private String lote;
    private Integer quantidade;
    private BigDecimal valor;


}
