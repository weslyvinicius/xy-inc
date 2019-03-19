package br.com.xyinc.endpoint.v1.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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

    @NotBlank(message = "validacao_default.campo_vazio")
    @Length(max = 255, message = "validacao_default.tamanho_maximo_do_campo")
    private String nome;

    @NotNull(message = "validacao_default.campo_vazio")
    @Min(value = 0, message = "validacao_default.campo_deve_ser_maior_que_zero")
    private Integer posicaoX;

    @NotNull(message = "validacao_default.campo_vazio")
    @Min(value = 0, message = "validacao_default.campo_deve_ser_maior_que_zero")
    private Integer posicaoY;
}
