package com.microservico.estoque.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Table(name = "tb_saida")
public class Saida implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_saida")
    private Long codigoSaida;

    @OneToOne
    @JoinColumn(name = "codigoLoja")
    private Loja loja;

    @OneToOne
    @JoinColumn(name = "codigoTransportadora")
    private Transportadora transportadora;

    @OneToMany
    @JoinColumn(name = "saida")
    private List<ItemSaida> itemSaidas;

    private BigDecimal total;
    private BigDecimal frete;
    private BigDecimal imposto;

}
