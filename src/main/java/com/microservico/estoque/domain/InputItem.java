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
@Table(name = "tb_item_entrada")
public class InputItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_item_entrada")
    private Long codigoItemEntrada;

    @OneToMany(mappedBy = "inputItem")
    @Column(name = "codigo_produto")
    private List<Product> products;

    @OneToOne
    @JoinColumn(name = "itemEntrada")
    private Input input;

    private String lote;
    private Integer quantidade;
    private BigDecimal valor;

}
