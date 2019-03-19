package br.com.xyinc.recurso.v1.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Criado por @autor wcardoso2  em 3/19/2019
 **/
@Data
@Builder
public class EstabelecimentoPorDistanciaDTO {

    @NotNull(message = "validacao_default.campo_vazio")
    @Min(value = 0, message = "validacao_default.campo_deve_ser_maior_que_zero")
    private Integer posicaoX;

    @NotNull(message = "validacao_default.campo_vazio")
    @Min(value = 0, message = "validacao_default.campo_deve_ser_maior_que_zero")
    private Integer posicaoY;

    @NotNull(message = "validacao_default.campo_vazio")
    @Min(value = 0, message = "validacao_default.campo_deve_ser_maior_que_zero")
    private Integer distanciaMaxima;
}
