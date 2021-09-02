package com.microservico.estoque.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Embeddable
public class Endereco implements Serializable {

    @NotBlank
    private String endereço;
    @NotBlank
    private Integer numero;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cep;
    @NotBlank
    private String contato;
    @NotBlank
    private String cnpj;
    @NotBlank
    private String telefone;
}
