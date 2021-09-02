package com.microservico.estoque.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Table(name = "tb_item_saida")
public class OutputItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_item_saida")
    private Long codigoItemSaida;

    @ManyToOne
    @JoinColumn(name = "codigo_saida")
    private Output output;

    @OneToOne(mappedBy = "outputItem")
    private Product product;

    private String lote;
    private Integer quantidade;
    private BigDecimal valor;


}
