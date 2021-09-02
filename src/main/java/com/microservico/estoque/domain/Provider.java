package com.microservico.estoque.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Table(name = "tb_fornecedor")
public class Provider implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_fornecedor")
    private Long codigoFornecedor;

    @ManyToOne
    @JoinColumn(name = "codigo_cidade")
    private City city;

    @OneToMany(mappedBy = "provider")
    private List<Product> products;

    private String fornecedor;

    @Embedded
    private Address address;


}
