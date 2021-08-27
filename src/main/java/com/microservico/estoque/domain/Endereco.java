package com.microservico.estoque.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Embeddable
public class Endereco implements Serializable {

    @Id
    private long id;
    private String endereço;
    private Integer numero;
    private String bairro;
    private String cep;
    private String contato;
    private String cnpj;
    private String telefone;
}
