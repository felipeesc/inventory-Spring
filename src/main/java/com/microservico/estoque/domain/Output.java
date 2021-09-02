package com.microservico.estoque.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Table(name = "tb_saida")
public class Output implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_saida")
    private Long codigoSaida;

    @OneToOne
    @JoinColumn(name = "codigoLoja")
    private Store store;

    @OneToOne
    @JoinColumn(name = "codigoTransportadora")
    private Transport transport;

    @OneToMany
    @JoinColumn(name = "saida")
    private List<OutputItem> outputItems;

    private BigDecimal total;
    private BigDecimal frete;
    private BigDecimal imposto;

}
