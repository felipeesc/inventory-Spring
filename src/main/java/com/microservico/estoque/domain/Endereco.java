package com.microservico.estoque.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Endereco implements Serializable {

    private String endereço;
    private Integer numero;
    private String bairro;
    private String cep;
    private String contato;
    private String cnpj;
    private String telefone;
}
