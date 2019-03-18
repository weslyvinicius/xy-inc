package br.com.xyinc.persistence.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;

/**
 * Criado por @autor wcardoso2  em 3/18/2019
 **/
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Table(name = "estabelecimento")
public class Estabelecimento extends AbstractEntity {

    @Column(name = "nome", nullable = false)
    private String nome;

    @Min(0)
    @Column(name = "posicao_x", nullable = false)
    private Integer posicaoX;

    @Min(0)
    @Column(name = "posicao_y", nullable = false)
    private Integer posicaoY;

    @Builder
    public Estabelecimento(Long id, String nome, Integer posicaoX, Integer posicaoY) {
        this.id = id;
        this.nome = nome;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }
}
