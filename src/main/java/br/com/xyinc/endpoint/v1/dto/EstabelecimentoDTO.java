package br.com.xyinc.endpoint.v1.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Criado por @autor wcardoso2  em 3/18/2019
 **/
@Data
@Builder
public class EstabelecimentoDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Min(0)
    private Integer posicaoX;

    @NotNull
    @Min(0)
    private Integer posicaoY;
}
