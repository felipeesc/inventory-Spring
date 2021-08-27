package com.microservico.estoque.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Builder
@Data
@ToString
public class CidadeDTO implements Serializable {

    @NotBlank
    private String cidade;

}
