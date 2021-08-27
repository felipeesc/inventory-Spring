package com.microservico.estoque.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Table(name = "tb_fornecedor")
public class Fornecedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_fornecedor")
    private Integer codigoFornecedor;

    @ManyToOne
    @JoinColumn(name = "codigo_cidade")
    private Cidade cidade;

    @OneToMany(mappedBy = "fornecedor")
    private List<Produto> produtos;

    private String fornecedor;

    @Embedded
    private Endereco endereco;


}
