package com.microservico.estoque.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Table(name = "tb_entrada")
public class Entrada implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_entrada")
    private Long codigoEntrada;

    @OneToMany
    @Column(name = "codigo_transportadora")
    private List<Transportadora> transportadoras;

    @OneToOne(mappedBy = "entrada")
    private ItemEntrada itemEntrada;

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @Column(name = "data_entrada")
    private LocalDateTime dataEntrada;

    private BigDecimal total;
    private BigDecimal frete;

    @Column(name = "numero_nota_fiscal")
    private Integer numeroNotaFiscal;

}
